package de.bauhd.minecraft.server.protocol.packet.play;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import de.bauhd.minecraft.server.api.command.CommandSender;
import de.bauhd.minecraft.server.protocol.Protocol;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import io.netty5.buffer.Buffer;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;

import java.util.ArrayDeque;
import java.util.List;

import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeString;
import static de.bauhd.minecraft.server.protocol.packet.PacketUtils.writeVarInt;

public final class Commands implements Packet {

    private RootCommandNode<CommandSender> rootNode;

    public Commands(final RootCommandNode<CommandSender> rootNode) {
        this.rootNode = rootNode;
    }

    public Commands() {}

    @Override
    public void decode(Buffer buf, Protocol.Version version) {

    }

    @Override
    public void encode(Buffer buf, Protocol.Version version) {
        final var nodeQueue = new ArrayDeque<CommandNode<CommandSender>>(List.of(this.rootNode));
        final var nodes = new Object2IntLinkedOpenHashMap<CommandNode<CommandSender>>();
        while (!nodeQueue.isEmpty()) {
            final var node = nodeQueue.poll();
            if (nodes.containsKey(node)) return;
            nodes.put(node, nodes.size());
            nodeQueue.addAll(node.getChildren());
            if (node.getRedirect() != null) {
                nodeQueue.add(node.getRedirect());
            }
        }

        writeVarInt(buf, nodes.size());
        for (final var node : nodes.keySet()) {
            this.writeNode(buf, node, nodes);
        }
        writeVarInt(buf, nodes.getInt(this.rootNode));
    }

    private void writeNode(final Buffer buf,
                               final CommandNode<CommandSender> node,
                               final Object2IntLinkedOpenHashMap<CommandNode<CommandSender>> nodes) {
        // node flags
        byte flags = 0;
        if (node.getRedirect() != null) {
            flags |= 0x08;
        }
        if (node.getCommand() != null) {
            flags |= 0x04;
        }

        if (node instanceof LiteralCommandNode<?>) {
            flags |= 0x01;
        } else if (node instanceof ArgumentCommandNode<?, ?>) {
            flags |= 0x02;
            if (((ArgumentCommandNode<CommandSender, ?>) node).getCustomSuggestions() != null) {
                flags |= 0x10;
            }
        }
        buf.writeByte(flags);

        // node children
        writeVarInt(buf, node.getChildren().size());
        for (final var child : node.getChildren()) {
            writeVarInt(buf, nodes.getInt(child));
        }

        // redirect node
        if (node.getRedirect() != null) {
            writeVarInt(buf, nodes.getInt(node.getRedirect()));
        }

        if (node instanceof ArgumentCommandNode<?,?> || node instanceof LiteralCommandNode<CommandSender>) {
            writeString(buf, node.getName()); // name

            if (node instanceof ArgumentCommandNode<?,?> argumentNode) {
                final var type = argumentNode.getType();

                // TODO make it better
                if (type.getClass() == IntegerArgumentType.class) {
                    final var argument = (IntegerArgumentType) type;
                    final var hasMinimum = argument.getMinimum() != Integer.MIN_VALUE;
                    final var hasMaximum = argument.getMaximum() != Integer.MAX_VALUE;

                    writeVarInt(buf, 3);

                    byte argumentFlags = 0;
                    if (hasMinimum) {
                        argumentFlags |= 0x01;
                    }
                    if (hasMaximum) {
                        argumentFlags |= 0x02;
                    }

                    buf.writeByte(argumentFlags);
                    if (hasMinimum) {
                        buf.writeInt(argument.getMinimum());
                    }
                    if (hasMaximum) {
                        buf.writeInt(argument.getMaximum());
                    }
                }

                // suggestion type
                if (argumentNode.getCustomSuggestions() != null) {
                    writeString(buf, "minecraft:ask_server");
                }
            }
        }
    }
}

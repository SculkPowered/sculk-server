package de.bauhd.minecraft.server.protocol.packet.play.command;

import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import de.bauhd.minecraft.server.command.CommandSender;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;

import java.util.ArrayDeque;
import java.util.List;

public final class Commands implements Packet {

    private final RootCommandNode<CommandSender> rootNode;

    public Commands(final RootCommandNode<CommandSender> rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public void encode(Buffer buf) {
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

        buf.writeVarInt(nodes.size());
        for (final var node : nodes.keySet()) {
            this.writeNode(buf, node, nodes);
        }
        buf.writeVarInt(nodes.getInt(this.rootNode));
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
        } else if (node instanceof ArgumentCommandNode<?, ?> argumentCommandNode) {
            flags |= 0x02;
            if (argumentCommandNode.getCustomSuggestions() != null) {
                flags |= 0x10;
            }
        }
        buf.writeByte(flags);

        // node children
        buf.writeVarInt(node.getChildren().size());
        for (final var child : node.getChildren()) {
            buf.writeVarInt(nodes.getInt(child));
        }

        // redirect node
        if (node.getRedirect() != null) {
            buf.writeVarInt(nodes.getInt(node.getRedirect()));
        }

        if (node instanceof ArgumentCommandNode<?,?> || node instanceof LiteralCommandNode<CommandSender>) {
            buf.writeString(node.getName()); // name

            if (node instanceof ArgumentCommandNode<?,?> argumentNode) {
                final var type = argumentNode.getType();
                if (type instanceof BoolArgumentType) {
                    buf.writeVarInt(0);
                } else if (type instanceof FloatArgumentType argument) {
                    final var hasMinimum = argument.getMinimum() != -Float.MAX_VALUE;
                    final var hasMaximum = argument.getMaximum() != Float.MAX_VALUE;

                    buf.writeVarInt(1);

                    byte argumentFlags = 0;
                    if (hasMinimum) {
                        argumentFlags |= 0x01;
                    }
                    if (hasMaximum) {
                        argumentFlags |= 0x02;
                    }

                    buf.writeByte(argumentFlags);
                    if (hasMinimum) {
                        buf.writeFloat(argument.getMinimum());
                    }
                    if (hasMaximum) {
                        buf.writeFloat(argument.getMaximum());
                    }
                } else if (type instanceof DoubleArgumentType argument) {
                    final var hasMinimum = argument.getMinimum() != -Double.MAX_VALUE;
                    final var hasMaximum = argument.getMaximum() != Double.MAX_VALUE;

                    buf.writeVarInt(2);

                    byte argumentFlags = 0;
                    if (hasMinimum) {
                        argumentFlags |= 0x01;
                    }
                    if (hasMaximum) {
                        argumentFlags |= 0x02;
                    }

                    buf.writeByte(argumentFlags);
                    if (hasMinimum) {
                        buf.writeDouble(argument.getMinimum());
                    }
                    if (hasMaximum) {
                        buf.writeDouble(argument.getMaximum());
                    }
                } else if (type instanceof IntegerArgumentType argument) {
                    final var hasMinimum = argument.getMinimum() != Integer.MIN_VALUE;
                    final var hasMaximum = argument.getMaximum() != Integer.MAX_VALUE;

                    buf.writeVarInt(3);

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
                } else if (type instanceof LongArgumentType argument) {
                    final var hasMinimum = argument.getMinimum() != Long.MIN_VALUE;
                    final var hasMaximum = argument.getMaximum() != Long.MAX_VALUE;

                    buf.writeVarInt(4);

                    byte argumentFlags = 0;
                    if (hasMinimum) {
                        argumentFlags |= 0x01;
                    }
                    if (hasMaximum) {
                        argumentFlags |= 0x02;
                    }

                    buf.writeByte(argumentFlags);
                    if (hasMinimum) {
                        buf.writeLong(argument.getMinimum());
                    }
                    if (hasMaximum) {
                        buf.writeLong(argument.getMaximum());
                    }
                } else if (type instanceof StringArgumentType argument) {
                    buf.writeVarInt(4).writeVarInt(argument.getType().ordinal());
                }

                // suggestion type
                if (argumentNode.getCustomSuggestions() != null) {
                    buf.writeString("minecraft:ask_server");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Commands{}";
    }
}

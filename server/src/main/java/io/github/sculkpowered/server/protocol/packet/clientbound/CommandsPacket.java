package io.github.sculkpowered.server.protocol.packet.clientbound;

import com.mojang.brigadier.tree.ArgumentCommandNode;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.mojang.brigadier.tree.RootCommandNode;
import io.github.sculkpowered.server.command.CommandSource;
import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.command.argument.SerializerRegistry;
import it.unimi.dsi.fastutil.objects.Object2IntLinkedOpenHashMap;
import java.util.ArrayDeque;
import java.util.List;

public final class CommandsPacket implements ClientboundPacket {

  private final RootCommandNode<CommandSource> rootNode;

  public CommandsPacket(final RootCommandNode<CommandSource> rootNode) {
    this.rootNode = rootNode;
  }

  @Override
  public void encode(Buffer buf) {
    final var nodeQueue = new ArrayDeque<CommandNode<CommandSource>>(List.of(this.rootNode));
    final var nodes = new Object2IntLinkedOpenHashMap<CommandNode<CommandSource>>();
    while (!nodeQueue.isEmpty()) {
      final var node = nodeQueue.poll();
      if (nodes.containsKey(node)) {
        continue;
      }
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
      final CommandNode<CommandSource> node,
      final Object2IntLinkedOpenHashMap<CommandNode<CommandSource>> nodes) {
    // node flags
    buf.writeByte(this.flags(node));

    // node children
    buf.writeVarInt(node.getChildren().size());
    for (final var child : node.getChildren()) {
      buf.writeVarInt(nodes.getInt(child));
    }

    // redirect node
    if (node.getRedirect() != null) {
      buf.writeVarInt(nodes.getInt(node.getRedirect()));
    }

    if (node instanceof LiteralCommandNode<CommandSource>) {
      buf.writeString(node.getName()); // name
    } else if (node instanceof ArgumentCommandNode<CommandSource, ?> argumentNode) {
      buf.writeString(node.getName()); // name

      SerializerRegistry.serialize(argumentNode.getType(), buf);

      // suggestion type
      if (argumentNode.getCustomSuggestions() != null) {
        buf.writeString("minecraft:ask_server");
      }
    }
  }

  private byte flags(final CommandNode<CommandSource> node) {
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
    return flags;
  }

  @Override
  public String toString() {
    return "CommandsPacket{}";
  }
}

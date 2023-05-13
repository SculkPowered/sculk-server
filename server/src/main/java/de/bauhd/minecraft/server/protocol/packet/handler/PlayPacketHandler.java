package de.bauhd.minecraft.server.protocol.packet.handler;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.entity.MinecraftPlayer;
import de.bauhd.minecraft.server.entity.player.GameMode;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;
import de.bauhd.minecraft.server.protocol.packet.play.*;
import de.bauhd.minecraft.server.protocol.packet.play.block.BlockUpdate;
import de.bauhd.minecraft.server.protocol.packet.play.command.ChatCommand;
import de.bauhd.minecraft.server.protocol.packet.play.container.ClickContainer;
import de.bauhd.minecraft.server.protocol.packet.play.container.ClickContainerButton;
import de.bauhd.minecraft.server.protocol.packet.play.container.CloseContainer;
import de.bauhd.minecraft.server.protocol.packet.play.position.*;
import de.bauhd.minecraft.server.world.MinecraftWorld;
import de.bauhd.minecraft.server.world.Position;
import de.bauhd.minecraft.server.world.block.Block;
import de.bauhd.minecraft.server.world.chunk.MinecraftChunk;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.ArrayList;

public final class PlayPacketHandler extends PacketHandler {

    private final Connection connection;
    private final AdvancedMinecraftServer server;
    private final MinecraftPlayer player;

    private ClientInformation clientInformation;

    public PlayPacketHandler(final Connection connection) {
        this.connection = connection;
        this.server = connection.server();
        this.player = connection.player();
    }

    @Override
    public boolean handle(ConfirmTeleportation confirmTeleportation) {
        return super.handle(confirmTeleportation);
    }

    @Override
    public boolean handle(ChatCommand chatCommand) {
        try {
            this.server.getCommandHandler().dispatcher().execute(chatCommand.command(), this.player);
        } catch (CommandSyntaxException e) {
            this.player.sendMessage(Component.text(e.getMessage(), NamedTextColor.RED));
        }
        return false;
    }

    @Override
    public boolean handle(ChatMessage chatMessage) {
        this.server.sendAll(
                new SystemChatMessage(Component.text(this.player.getUsername() + " - " + chatMessage.message()), false));
        return false;
    }

    @Override
    public boolean handle(ClientCommand clientCommand) {
        if (clientCommand.actionId() == 1) {
            connection.send(new AwardStatistics());
        }
        return false;
    }

    @Override
    public boolean handle(ClientInformation clientInformation) {
        if (this.clientInformation == null || this.clientInformation.skinParts() != clientInformation.skinParts()) {
            this.player.sendViewersAndSelf(new EntityMetadata(this.player.getId(), buf ->
                    buf.writeUnsignedByte(17).writeVarInt(0).writeByte((byte) clientInformation.skinParts())));
        }
        this.clientInformation = clientInformation;
        return false;
    }

    @Override
    public boolean handle(ClickContainerButton clickContainerButton) {
        return super.handle(clickContainerButton);
    }

    @Override
    public boolean handle(ClickContainer clickContainer) {
        return super.handle(clickContainer);
    }

    @Override
    public boolean handle(CloseContainer closeContainer) {
        return super.handle(closeContainer);
    }

    @Override
    public boolean handle(PluginMessage pluginMessage) {
        /*final var buf = DefaultBufferAllocators.offHeapAllocator().allocate(this.data.length);
        buf.writeBytes(this.data);
        System.out.println(PacketUtils.readString(buf))*/
        return false;
    }

    @Override
    public boolean handle(Interact interact) {
        return super.handle(interact);
    }

    @Override
    public boolean handle(KeepAlive keepAlive) {
        this.player.setKeepAlivePending(false);
        return false;
    }

    @Override
    public boolean handle(PlayerPosition playerPosition) {
        final var x = playerPosition.x();
        final var y = playerPosition.y();
        final var z = playerPosition.z();

        final var position = this.player.getPosition();
        this.player.sendViewers(new EntityPosition(this.player.getId(),
                this.delta(position.x(), x), this.delta(position.y(), y), this.delta(position.z(), z), playerPosition.onGround()));
        this.player.setPosition(new Position(x, y, z, position.yaw(), position.pitch()));
        this.calculateChunks(position, this.player.getPosition());
        return false;
    }

    @Override
    public boolean handle(PlayerPositionAndRotation playerPositionAndRotation) {
        final var x = playerPositionAndRotation.x();
        final var y = playerPositionAndRotation.y();
        final var z = playerPositionAndRotation.z();
        final var yaw = playerPositionAndRotation.yaw();
        final var pitch = playerPositionAndRotation.pitch();

        final var position = this.player.getPosition();
        this.player.sendViewers(new EntityPositionAndRotation(this.player.getId(),
                        this.delta(position.x(), x), this.delta(position.y(), y), this.delta(position.z(), z),
                        yaw, pitch, playerPositionAndRotation.onGround()),
                new HeadRotation(this.player.getId(), yaw));
        this.player.setPosition(new Position(x, y, z, yaw, pitch));
        this.calculateChunks(position, this.player.getPosition());
        return false;
    }

    @Override
    public boolean handle(PlayerRotation playerRotation) {
        final var yaw = playerRotation.yaw();
        final var pitch = playerRotation.pitch();

        final var position = this.player.getPosition();
        this.player.sendViewers(
                new EntityRotation(this.player.getId(), yaw, pitch, playerRotation.onGround()),
                new HeadRotation(this.player.getId(), yaw)
        );
        this.player.setPosition(new Position(position.x(), position.y(), position.z(), yaw, pitch));
        return false;
    }

    @Override
    public boolean handle(PlayerOnGround playerOnGround) {
        return super.handle(playerOnGround);
    }

    @Override
    public boolean handle(PlayerAction playerAction) {
        switch (playerAction.status()) {
            case 0 -> this.player.getWorld().setBlock(playerAction.position(), Block.AIR);  // only if instant break
            case 3 -> this.player.setItem((short) (36 + this.player.getHeldItemSlot()), null); // TODO: inventory
            case 4 -> {
                final var itemInHand = this.player.getItemInMainHand();
                if (itemInHand != null) {
                    itemInHand.amount(itemInHand.amount() - 1);
                }
            }
            case 5 -> {}
            case 6 -> {
                // TODO: inventory - swap item
            }
        }
        return false;
    }

    @Override
    public boolean handle(PlayerCommand playerCommand) {
        /*if (this.action == Action.START_SNEAKING) {
            final var packet = new EntityMetadata(connection.player().getId(), 6, 18, 5);
            for (final var otherPlayer : Worker.PLAYERS) {
                if (otherPlayer == player) continue;
                otherPlayer.send(packet);
            }
        } else if (this.action == Action.STOP_SNEAKING) {
            final var packet = new EntityMetadata(connection.player().getId(), 6, 18, 0);
            for (final var otherPlayer : Worker.PLAYERS) {
                if (otherPlayer == player) continue;
                otherPlayer.send(packet);
            }
        }*/
        return false;
    }

    @Override
    public boolean handle(HeldItem heldItem) {
        this.player.setHeldItemSlot(heldItem.slot());
        return false;
    }

    @Override
    public boolean handle(CreativeModeSlot creativeModeSlot) {
        final var player = connection.player();
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.sendMessage(AdvancedMinecraftServer.SUS_COMPONENT);
            return false;
        }
        player.setItem(creativeModeSlot.slot(), creativeModeSlot.clickedItem());
        return false;
    }

    @Override
    public boolean handle(SwingArm swingArm) {
        this.player.sendViewers(new EntityAnimation(this.player.getId(), (byte) (swingArm.hand() == 1 ? 3 : 0)));
        return false;
    }

    @Override
    public boolean handle(TeleportToEntity teleportToEntity) {
        if (this.player.getGameMode() == GameMode.SPECTATOR) {
            // TODO: teleport to entity
        } else {
            this.player.sendMessage(AdvancedMinecraftServer.SUS_COMPONENT);
        }
        return false;
    }

    @Override
    public boolean handle(UseItemOn useItemOn) {
        final var slot = this.player.getItem(this.player.getHeldItemSlot() + 36);
        if (slot == null) {
            return false;
        }
        var position = useItemOn.position();
        position = switch (useItemOn.face()) {
            case BOTTOM -> position.subtract(0, 1, 0);
            case TOP -> position.add(0, 1, 0);
            case NORTH -> position.subtract(0, 0, 1);
            case SOUTH -> position.add(0, 0, 1);
            case WEST -> position.subtract(1, 0, 0);
            case EAST -> position.add(1, 0, 0);
        };
        this.server.sendAll(new BlockUpdate(position, slot.material().ordinal()));
        return false;
    }

    @Override
    public boolean handle(UseItem useItem) {
        return super.handle(useItem);
    }

    private short delta(final double previous, final double current) {
        return (short) ((current * 32 - previous * 32) * 128);
    }

    private void calculateChunks(final Position from, final Position to) {
        final var fromChunkX = (int) from.x() >> 4;
        final var fromChunkZ = (int) from.z() >> 4;
        final var chunkX = (int) to.x() >> 4;
        final var chunkZ = (int) to.z() >> 4;
        if (fromChunkX != chunkX || fromChunkZ != chunkZ) {
            this.player.send(new CenterChunk(chunkX, chunkZ));
            final var world = ((MinecraftWorld) this.player.getWorld());
            final var chunks = new ArrayList<MinecraftChunk>();
            this.connection.forChunksInRange(chunkX, chunkZ, 10, (x, z) -> {
                final var chunk = world.getChunk(x, z);
                chunks.add(chunk);
                chunk.viewers().add(this.player); // new in range
            });
            this.connection.forChunksInRange(fromChunkX, fromChunkZ, 10, (x, z) -> {
                final var chunk = world.getChunk(x, z);
                if (!chunks.contains(chunk)) {
                    chunk.viewers().remove(this.player); // chunk not in range
                } else {
                    chunks.remove(chunk); // already loaded
                }
            });
            for (final var chunk : chunks) { // send all new chunks
                chunk.send(this.player);
            }
        }
    }
}

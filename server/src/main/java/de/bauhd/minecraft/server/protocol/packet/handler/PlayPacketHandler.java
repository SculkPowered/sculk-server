package de.bauhd.minecraft.server.protocol.packet.handler;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.bauhd.minecraft.server.AdvancedMinecraftServer;
import de.bauhd.minecraft.server.container.item.ItemStack;
import de.bauhd.minecraft.server.entity.Entity;
import de.bauhd.minecraft.server.entity.player.GameMode;
import de.bauhd.minecraft.server.entity.player.MinecraftPlayer;
import de.bauhd.minecraft.server.event.player.PlayerClickContainerButtonEvent;
import de.bauhd.minecraft.server.event.player.PlayerClickContainerEvent;
import de.bauhd.minecraft.server.container.MineContainer;
import de.bauhd.minecraft.server.protocol.Connection;
import de.bauhd.minecraft.server.protocol.packet.PacketHandler;
import de.bauhd.minecraft.server.protocol.packet.play.*;
import de.bauhd.minecraft.server.protocol.packet.play.command.ChatCommand;
import de.bauhd.minecraft.server.protocol.packet.play.container.*;
import de.bauhd.minecraft.server.protocol.packet.play.position.*;
import de.bauhd.minecraft.server.util.ItemList;
import de.bauhd.minecraft.server.world.Position;
import de.bauhd.minecraft.server.world.block.Block;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PlayPacketHandler extends PacketHandler {

    private static final Logger LOGGER = LogManager.getLogger(PlayPacketHandler.class);

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
        return true;
    }

    @Override
    public boolean handle(ChatCommand chatCommand) {
        try {
            this.server.getCommandHandler().dispatcher().execute(chatCommand.command(), this.player);
        } catch (CommandSyntaxException e) {
            this.player.sendMessage(Component.text(e.getMessage(), NamedTextColor.RED));
        }
        return true;
    }

    @Override
    public boolean handle(ChatMessage chatMessage) {
        this.server.sendAll(
                new SystemChatMessage(Component.text(this.player.getUsername() + " - " + chatMessage.message()), false));
        return true;
    }

    @Override
    public boolean handle(ClientCommand clientCommand) {
        if (clientCommand.actionId() == 1) {
            this.connection.send(new AwardStatistics());
        }
        return true;
    }

    @Override
    public boolean handle(ClientInformation clientInformation) {
        if (this.clientInformation == null || this.clientInformation.skinParts() != clientInformation.skinParts()) {
            this.player.metadata.setByte(17, (byte) clientInformation.skinParts());
        }
        if (this.clientInformation == null || this.clientInformation.mainHand() != clientInformation.mainHand()) {
            this.player.metadata.setByte(18, (byte) clientInformation.mainHand());
        }
        this.clientInformation = clientInformation;
        return true;
    }

    @Override
    public boolean handle(ClickContainerButton clickContainerButton) {
        if (this.player.getContainer() == null) { // there should be a container
            this.player.send(new CloseContainer(1));
            return true;
        }
        this.server.getEventHandler().call(new PlayerClickContainerButtonEvent(this.player, clickContainerButton.buttonId()))
                .exceptionally(throwable -> {
            LOGGER.error("Exception while handling container click button for " + this.player.getUsername(), throwable);
            return null;
        });
        return true;
    }

    @Override
    public boolean handle(ClickContainer clickContainer) {
        final var inventory = this.player.getInventory();
        final var container = (this.player.getContainer() != null ? this.player.getContainer() : inventory);
        this.server.getEventHandler().call(new PlayerClickContainerEvent(this.player, container, clickContainer.carriedItem(), clickContainer.slot()))
                .thenAcceptAsync(event -> {
                    if (event.isCancelled()) { // let's resend to override client prediction
                        if (container == inventory) {
                            this.player.send(new ContainerContent((byte) 0, 1, inventory.items));
                        } else {
                            final var mineContainer = (MineContainer) container;
                            final var items = new ItemList(container.type().size() + 36);
                            for (var i = 8; i < 44; i++) {
                                items.set(i - 9 + container.type().size(), inventory.items.get(i));
                            }
                            for (var i = 0; i < mineContainer.items.size(); i++) {
                                items.set(i, mineContainer.items.get(i));
                            }
                            this.player.send(new ContainerContent((byte) 1, 1, items));
                        }
                    } else {
                        clickContainer.slots().forEach(inventory.items::set);
                    }
                }, this.connection.executor())
                .exceptionally(throwable -> {
                    LOGGER.error("Exception while handling container click for " + this.player.getUsername(), throwable);
                    return null;
                });
        return true;
    }

    @Override
    public boolean handle(CloseContainer closeContainer) {
        if (this.player.getContainer() != null) {
            this.player.getContainer().removeViewer(this.player);
            this.player.setContainer(null);
        }
        return true;
    }

    @Override
    public boolean handle(PluginMessage pluginMessage) {
        /*final var buf = DefaultBufferAllocators.offHeapAllocator().allocate(this.data.length);
        buf.writeBytes(this.data);
        System.out.println(PacketUtils.readString(buf))*/
        return true;
    }

    @Override
    public boolean handle(Interact interact) {
        return true;
    }

    @Override
    public boolean handle(KeepAlive keepAlive) {
        this.player.setKeepAlivePending(false);
        return true;
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
        this.connection.calculateChunks(position, this.player.getPosition());
        return true;
    }

    @Override
    public boolean handle(PlayerPositionAndRotation playerPositionAndRotation) {
        final var x = playerPositionAndRotation.x();
        final var y = playerPositionAndRotation.y();
        final var z = playerPositionAndRotation.z();
        final var yaw = playerPositionAndRotation.yaw();
        final var pitch = playerPositionAndRotation.pitch();

        final var position = this.player.getPosition();
        this.player.sendViewers(
                new EntityPositionAndRotation(this.player.getId(),
                        this.delta(position.x(), x), this.delta(position.y(), y), this.delta(position.z(), z),
                        yaw, pitch, playerPositionAndRotation.onGround()),
                new HeadRotation(this.player.getId(), yaw));
        this.player.setPosition(new Position(x, y, z, yaw, pitch));
        this.connection.calculateChunks(position, this.player.getPosition());
        return true;
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
        return true;
    }

    @Override
    public boolean handle(PlayerOnGround playerOnGround) {
        return true;
    }

    @Override
    public boolean handle(PlayerAbilities playerAbilities) {
        this.player.flying = playerAbilities.flags() == 2;
        return true;
    }

    @Override
    public boolean handle(PlayerAction playerAction) {
        switch (playerAction.status()) {
            case 0 -> { // started digging
                if (this.player.canInstantBreak()) {
                    this.player.getWorld().setBlock(playerAction.position(), Block.AIR);
                }
            }
            case 1 -> { // cancelled digging

            }
            case 2 -> this.player.getWorld().setBlock(playerAction.position(), Block.AIR); // finished digging
            case 3 -> this.player.getInventory().setItem(this.player.getHeldItemSlot(), null); // drop stack
            case 4 -> { // drop item
                final var itemInHand = this.player.getInventory().getItemInMainHand();
                if (itemInHand != ItemStack.AIR) {
                    itemInHand.amount(itemInHand.amount() - 1);
                }
            }
            case 5 -> { // shoot arrow / finish eating

            }
            case 6 -> { // swap item
                final var inventory = this.player.getInventory();
                final var itemInMainHand = inventory.getItemInMainHand();
                final var itemInOffHand = inventory.getItemInOffHand();
                inventory.setItem(this.player.getHeldItemSlot(), itemInOffHand);
                inventory.setItemInOffHand(itemInMainHand);
            }
        }
        return true;
    }

    @Override
    public boolean handle(PlayerCommand playerCommand) {
        if (playerCommand.action() == PlayerCommand.Action.START_SNEAKING) {
            this.player.setPose(Entity.Pose.SNEAKING);
        } else if (playerCommand.action() == PlayerCommand.Action.STOP_SNEAKING) {
            this.player.setPose(Entity.Pose.STANDING);
        }
        return true;
    }

    @Override
    public boolean handle(HeldItem heldItem) {
        this.player.heldItem = heldItem.slot();
        return true;
    }

    @Override
    public boolean handle(CreativeModeSlot creativeModeSlot) {
        final var player = this.connection.player();
        if (player.getGameMode() != GameMode.CREATIVE) {
            LOGGER.info(this.player.getUsername() + " tried to set a slot, but is not in creative mode.");
            return false;
        }
        var index = creativeModeSlot.slot();
        if (index < 9) {
            index += 36;
        } else if (index < 45) {
            index -= 36;
        } else if (index > 45) {
            index -= 5;
        }
        player.getInventory().items.add(index, creativeModeSlot.clickedItem());
        return true;
    }

    @Override
    public boolean handle(SwingArm swingArm) {
        this.player.sendViewers(new EntityAnimation(this.player.getId(), (byte) (swingArm.hand() == 1 ? 3 : 0)));
        return true;
    }

    @Override
    public boolean handle(TeleportToEntity teleportToEntity) {
        if (this.player.getGameMode() == GameMode.SPECTATOR) {
            // TODO: teleport to entity
        } else {
            LOGGER.info(this.player.getUsername() + " tried to teleport, but is not in spectator mode.");
        }
        return true;
    }

    @Override
    public boolean handle(UseItemOn useItemOn) {
        final var slot = this.player.getInventory().getItemInMainHand();
        if (slot == ItemStack.AIR) return false;
        var position = useItemOn.position();
        position = switch (useItemOn.face()) {
            case BOTTOM -> position.subtract(0, 1, 0);
            case TOP -> position.add(0, 1, 0);
            case NORTH -> position.subtract(0, 0, 1);
            case SOUTH -> position.add(0, 0, 1);
            case WEST -> position.subtract(1, 0, 0);
            case EAST -> position.add(1, 0, 0);
        };
        this.player.getWorld().setBlock(position, Block.get("minecraft:" + slot.material().name().toLowerCase()));
        return true;
    }

    @Override
    public boolean handle(UseItem useItem) {
        return true;
    }

    private short delta(final double previous, final double current) {
        return (short) ((current * 32 - previous * 32) * 128);
    }
}

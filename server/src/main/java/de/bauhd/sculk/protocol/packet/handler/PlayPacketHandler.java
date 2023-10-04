package de.bauhd.sculk.protocol.packet.handler;

import de.bauhd.sculk.SculkServer;
import de.bauhd.sculk.container.SculkContainer;
import de.bauhd.sculk.container.item.ItemStack;
import de.bauhd.sculk.entity.Entity;
import de.bauhd.sculk.entity.player.GameMode;
import de.bauhd.sculk.entity.player.SculkPlayer;
import de.bauhd.sculk.event.block.BlockBreakEvent;
import de.bauhd.sculk.event.block.BlockPlaceEvent;
import de.bauhd.sculk.event.player.PlayerChatEvent;
import de.bauhd.sculk.event.player.PlayerClickContainerButtonEvent;
import de.bauhd.sculk.event.player.PlayerClickContainerEvent;
import de.bauhd.sculk.event.player.PlayerUseItemEvent;
import de.bauhd.sculk.protocol.SculkConnection;
import de.bauhd.sculk.protocol.packet.PacketHandler;
import de.bauhd.sculk.protocol.packet.play.*;
import de.bauhd.sculk.protocol.packet.play.block.BlockUpdate;
import de.bauhd.sculk.protocol.packet.play.command.ChatCommand;
import de.bauhd.sculk.protocol.packet.play.container.ClickContainer;
import de.bauhd.sculk.protocol.packet.play.container.ClickContainerButton;
import de.bauhd.sculk.protocol.packet.play.container.CloseContainer;
import de.bauhd.sculk.protocol.packet.play.container.ContainerContent;
import de.bauhd.sculk.protocol.packet.play.position.*;
import de.bauhd.sculk.util.ItemList;
import de.bauhd.sculk.world.Position;
import de.bauhd.sculk.world.block.Block;
import de.bauhd.sculk.world.block.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static de.bauhd.sculk.world.block.Block.Facing.*;

public final class PlayPacketHandler extends PacketHandler {

    private static final Logger LOGGER = LogManager.getLogger(PlayPacketHandler.class);

    private final SculkConnection connection;
    private final SculkServer server;
    private final SculkPlayer player;

    public PlayPacketHandler(final SculkConnection connection, final SculkServer server, final SculkPlayer player) {
        this.connection = connection;
        this.server = server;
        this.player = player;
    }

    @Override
    public boolean handle(ConfirmTeleportation confirmTeleportation) {
        return true;
    }

    @Override
    public boolean handle(ChatCommand chatCommand) {
        this.server.getCommandHandler().execute(this.player, chatCommand.command());
        return true;
    }

    @Override
    public boolean handle(ChatMessage chatMessage) {
        this.server.getEventHandler().call(new PlayerChatEvent(this.player, chatMessage.message())).exceptionally(throwable -> {
            LOGGER.error("Exception while handling PlayerChatEvent for " + this.player.getUsername(), throwable);
            return null;
        });
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
        final var settings = this.player.getSettings();
        final var old = settings.clientInformation();
        if (settings.isDefault() || old.skinParts() != clientInformation.skinParts()) {
            this.player.metadata.setByte(17, (byte) clientInformation.skinParts());
        }
        if (settings.isDefault() || old.mainHand() != clientInformation.mainHand()) {
            this.player.metadata.setByte(18, (byte) clientInformation.mainHand().ordinal());
        }
        if (old.viewDistance() != clientInformation.viewDistance()) {
            this.connection
                    .calculateChunks(this.player.getPosition(), this.player.getPosition(), false, true,
                            clientInformation.viewDistance(), old.viewDistance());
        }
        this.player.getSettings().setClientInformation(clientInformation);
        return true;
    }

    @Override
    public boolean handle(CommandSuggestionsRequest commandSuggestionsRequest) {
        // TODO: implement suggestion response
        return true;
    }

    @Override
    public boolean handle(ClickContainerButton clickContainerButton) {
        if (this.player.getOpenedContainer() == null) { // there should be a container
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
        final var container = (this.player.getOpenedContainer() != null ? this.player.getOpenedContainer() : inventory);
        this.server.getEventHandler().call(new PlayerClickContainerEvent(this.player, container, clickContainer.carriedItem(), clickContainer.slot()))
                .thenAcceptAsync(event -> {
                    if (event.getResult().isDenied()) { // let's resend to override client prediction
                        if (container == inventory) {
                            this.player.send(new ContainerContent((byte) 0, 1, inventory.items));
                        } else {
                            final var sculkContainer = (SculkContainer) container;
                            final var items = new ItemList(container.getType().size() + 36);
                            for (var i = 8; i < 44; i++) {
                                items.set(i - 9 + container.getType().size(), inventory.items.get(i));
                            }
                            for (var i = 0; i < sculkContainer.items.size(); i++) {
                                items.set(i, sculkContainer.items.get(i));
                            }
                            this.player.send(new ContainerContent((byte) 1, 1, items));
                        }
                    } else {
                        for (final var entry : clickContainer.slots().int2ObjectEntrySet()) {
                            inventory.items.set(entry.getIntKey(), entry.getValue());
                        }
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
        if (this.player.getOpenedContainer() != null) {
            this.player.getOpenedContainer().removeViewer(this.player);
            this.player.setContainer(null);
        }
        return true;
    }

    @Override
    public boolean handle(PluginMessage pluginMessage) {
        // TODO: implement plugin message system
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
                    this.callBlockBreak(playerAction.position());
                }
            }
            case 1 -> { // cancelled digging

            }
            case 2 -> this.callBlockBreak(playerAction.position()); // finished digging
            case 3 -> this.player.getInventory().setItem(this.player.getHeldItemSlot(), ItemStack.empty()); // drop stack
            case 4 -> { // drop item
                final var itemInHand = this.player.getInventory().getItemInMainHand();
                if (!itemInHand.isEmpty()) {
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

    private void callBlockBreak(Position position) {
        this.server.getEventHandler().call(new BlockBreakEvent(this.player, position, this.player.getWorld().getBlock(position)))
                .thenAcceptAsync(event -> {
                    if (event.getResult().isAllowed()) {
                        this.player.getWorld().setBlock(position, Block.AIR);
                    } else {
                        this.player.getWorld().getChunkAt((int) position.x(), (int) position.y()).send(this.player);
                    }
                }, this.connection.executor())
                .exceptionally(throwable -> {
                    LOGGER.error("Exception while block break from " + this.player.getUsername(), throwable);
                    return null;
                });
    }

    @Override
    public boolean handle(PlayerCommand playerCommand) {
        switch (playerCommand.action()) {
            case START_SNEAKING -> this.player.setPose(Entity.Pose.SNEAKING);
            case STOP_SNEAKING -> this.player.setPose(Entity.Pose.STANDING);
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
        if (this.player.getGameMode() != GameMode.CREATIVE) {
            LOGGER.info(this.player.getUsername() + " tried to set a slot, but is not in creative mode.");
            return false;
        }
        this.player.getInventory().items.set(creativeModeSlot.slot(), creativeModeSlot.clickedItem());
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
            final var target = this.server.getPlayer(teleportToEntity.target());
            if (target != null) {
                if (target.getWorld() != this.player.getWorld()) {
                    this.player.setWorld(target.getWorld());
                }
                final var old = this.player.getPosition();
                final var position = target.getPosition();
                this.player.send(new EntityPositionAndRotation(this.player.getId(),
                        this.delta(old.x(), position.x()),
                        this.delta(old.y(), position.y()),
                        this.delta(old.y(), position.y()),
                        position.yaw(),
                        position.pitch(),
                        true
                ));
            }
        } else {
            LOGGER.info(this.player.getUsername() + " tried to teleport, but is not in spectator mode.");
        }
        return true;
    }

    @Override
    public boolean handle(UseItemOn useItemOn) {
        final var inventory = this.player.getInventory();
        final var slot = (useItemOn.hand() == 0 ? inventory.getItemInMainHand() : inventory.getItemInOffHand());
        this.server.getEventHandler().call(new PlayerUseItemEvent(this.player, slot)).thenAcceptAsync(event -> {
            if (slot.isEmpty()) return;
            var position = this.calculatePosition(useItemOn.position(), useItemOn.face());
            if (this.player.getOpenedContainer() != null) {
                this.player.getWorld().getChunkAt((int) position.x(), (int) position.z()).send(this.player); // TODO: not send the complete chunk
                if (useItemOn.hand() == 0) {
                    inventory.setItemInMainHand(slot);
                } else {
                    inventory.setItemInOffHand(slot);
                }
                return;
            }

            final var currentBlock = this.player.getWorld().getBlock(position);
            if (currentBlock != Block.AIR) return; // block at position, don't set
            var block = Block.get(slot.material().key());
            if (block == null) return;
            if (block instanceof BlockState.Facing<?> facing) { // let's set the correct facing
                final var rotation = (int) Math.floor(this.player.getPosition().yaw() / 90.0D + 0.5D) & 3;
                block = facing.facing(switch (rotation % 4) {
                    case 0 -> SOUTH;
                    case 1 -> WEST;
                    case 2 -> NORTH;
                    case 3 -> EAST;
                    default -> null;
                });
            }
            this.server.getEventHandler().call(new BlockPlaceEvent(this.player, position, block)).thenAcceptAsync(placeEvent -> {
                if (placeEvent.getResult().isAllowed()) {
                    this.player.getWorld().setBlock(placeEvent.getPosition(), placeEvent.getBlock());
                } else {
                    this.player.send(new BlockUpdate(placeEvent.getPosition(), currentBlock.getId()));
                }
            }, this.connection.executor()).exceptionally(throwable -> {
                LOGGER.error("Exception while handling block place for " + this.player.getUsername(), throwable);
                return null;
            });
        }, this.connection.executor());
        return true;
    }

    @Override
    public boolean handle(UseItem useItem) {
        final var inventory = this.player.getInventory();
        this.server.getEventHandler().call(new PlayerUseItemEvent(this.player,
                (useItem.hand() == 0 ? inventory.getItemInMainHand() : inventory.getItemInOffHand())));
        return true;
    }

    private Position calculatePosition(Position position, Block.Facing facing) {
        if (facing == DOWN) {
            position = position.subtract(0, 1, 0);
        } else if (facing == UP) {
            position = position.add(0, 1, 0);
        } else if (facing == NORTH) {
            position = position.subtract(0, 0, 1);
        } else if (facing == SOUTH) {
            position = position.add(0, 0, 1);
        } else if (facing == WEST) {
            position = position.subtract(1, 0, 0);
        } else if (facing == EAST) {
            position = position.add(1, 0, 0);
        }
        return position;
    }

    private short delta(final double previous, final double current) {
        return (short) ((current * 32 - previous * 32) * 128);
    }
}

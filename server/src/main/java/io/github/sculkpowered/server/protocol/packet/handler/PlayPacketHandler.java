package io.github.sculkpowered.server.protocol.packet.handler;

import static io.github.sculkpowered.server.world.block.Block.Facing.DOWN;
import static io.github.sculkpowered.server.world.block.Block.Facing.EAST;
import static io.github.sculkpowered.server.world.block.Block.Facing.NORTH;
import static io.github.sculkpowered.server.world.block.Block.Facing.SOUTH;
import static io.github.sculkpowered.server.world.block.Block.Facing.UP;
import static io.github.sculkpowered.server.world.block.Block.Facing.WEST;

import com.mojang.brigadier.CommandDispatcher;
import io.github.sculkpowered.server.SculkServer;
import io.github.sculkpowered.server.container.item.ItemStack;
import io.github.sculkpowered.server.entity.Entity;
import io.github.sculkpowered.server.entity.player.GameMode;
import io.github.sculkpowered.server.entity.player.SculkPlayer;
import io.github.sculkpowered.server.event.block.BlockBreakEvent;
import io.github.sculkpowered.server.event.block.BlockPlaceEvent;
import io.github.sculkpowered.server.event.player.PlayerAttackedEntityEvent;
import io.github.sculkpowered.server.event.player.PlayerChatEvent;
import io.github.sculkpowered.server.event.player.PlayerClickContainerButtonEvent;
import io.github.sculkpowered.server.event.player.PlayerClickContainerEvent;
import io.github.sculkpowered.server.event.player.PlayerUseItemEvent;
import io.github.sculkpowered.server.protocol.SculkConnection;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.play.AwardStatistics;
import io.github.sculkpowered.server.protocol.packet.play.ChatMessage;
import io.github.sculkpowered.server.protocol.packet.play.ClientCommand;
import io.github.sculkpowered.server.protocol.packet.play.ClientInformation;
import io.github.sculkpowered.server.protocol.packet.play.ConfirmTeleportation;
import io.github.sculkpowered.server.protocol.packet.play.CreativeModeSlot;
import io.github.sculkpowered.server.protocol.packet.play.EntityAnimation;
import io.github.sculkpowered.server.protocol.packet.play.Equipment;
import io.github.sculkpowered.server.protocol.packet.play.HeldItem;
import io.github.sculkpowered.server.protocol.packet.play.Interact;
import io.github.sculkpowered.server.protocol.packet.play.KeepAlive;
import io.github.sculkpowered.server.protocol.packet.play.PlayerAbilities;
import io.github.sculkpowered.server.protocol.packet.play.PlayerAction;
import io.github.sculkpowered.server.protocol.packet.play.PlayerCommand;
import io.github.sculkpowered.server.protocol.packet.play.PluginMessage;
import io.github.sculkpowered.server.protocol.packet.play.SwingArm;
import io.github.sculkpowered.server.protocol.packet.play.TeleportToEntity;
import io.github.sculkpowered.server.protocol.packet.play.UseItem;
import io.github.sculkpowered.server.protocol.packet.play.UseItemOn;
import io.github.sculkpowered.server.protocol.packet.play.block.BlockAcknowledge;
import io.github.sculkpowered.server.protocol.packet.play.command.ChatCommand;
import io.github.sculkpowered.server.protocol.packet.play.command.CommandSuggestionsRequest;
import io.github.sculkpowered.server.protocol.packet.play.command.CommandSuggestionsResponse;
import io.github.sculkpowered.server.protocol.packet.play.container.ClickContainer;
import io.github.sculkpowered.server.protocol.packet.play.container.ClickContainerButton;
import io.github.sculkpowered.server.protocol.packet.play.container.CloseContainer;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerOnGround;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerPosition;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerPositionAndRotation;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerRotation;
import io.github.sculkpowered.server.util.OneInt2ObjectMap;
import io.github.sculkpowered.server.world.Position;
import io.github.sculkpowered.server.world.block.Block;
import io.github.sculkpowered.server.world.block.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PlayPacketHandler extends PacketHandler {

  private static final Logger LOGGER = LogManager.getLogger(PlayPacketHandler.class);

  private final SculkConnection connection;
  private final SculkServer server;
  private final SculkPlayer player;

  public PlayPacketHandler(
      final SculkConnection connection,
      final SculkServer server,
      final SculkPlayer player
  ) {
    this.connection = connection;
    this.server = server;
    this.player = player;
  }

  @Override
  public boolean handle(ConfirmTeleportation confirmTeleportation) {
    this.player.receivedTeleportConfirmation(true);
    return true;
  }

  @Override
  public boolean handle(ChatCommand chatCommand) {
    this.server.commandHandler().execute(this.player, chatCommand.command());
    return true;
  }

  @Override
  public boolean handle(ChatMessage chatMessage) {
    this.server.eventHandler().call(new PlayerChatEvent(this.player, chatMessage.message()))
        .exceptionally(throwable -> {
          LOGGER.error("Exception while handling PlayerChatEvent for " + this.player.name(),
              throwable);
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
    this.player.handleClientInformation(clientInformation);
    return true;
  }

  @Override
  public boolean handle(CommandSuggestionsRequest request) {
    final var command = request.text();
    final var start = command.lastIndexOf(CommandDispatcher.ARGUMENT_SEPARATOR_CHAR) + 1;
    this.server.commandHandler().suggestions(this.player, command.substring(1))
        .thenAcceptAsync(suggestions -> this.player.send(
                new CommandSuggestionsResponse(request.transactionId(),
                    start, command.length() - start, suggestions.getList())),
            this.connection.executor())
        .exceptionally(throwable -> {
          LOGGER.error("Exception during suggestion response", throwable);
          return null;
        });
    return true;
  }

  @Override
  public boolean handle(ClickContainerButton clickContainerButton) {
    final var container = this.player.openedContainer();
    if (container == null) { // there should be a container
      this.player.send(new CloseContainer(1));
      return true;
    }
    this.server.eventHandler()
        .call(new PlayerClickContainerButtonEvent(this.player, container,
            clickContainerButton.buttonId()))
        .exceptionally(throwable -> {
          LOGGER.error(
              "Exception while handling container click button for " + this.player.name(),
              throwable);
          return null;
        });
    return true;
  }

  @Override
  public boolean handle(ClickContainer clickContainer) {
    final var inventory = this.player.inventory();
    var container = (this.player.openedContainer() != null
        ? this.player.openedContainer() : inventory);
    var slot = clickContainer.slot();
    if (container == inventory && slot > inventory.type().size()) {
      return true; // should not be possible
    }
    if (slot >= container.type().size()) { // clicked in inventory
      // calculate inventory slot, without inner inventory (equipment, crafting)
      slot -= (short) (container.type().size() - 9);
      container = inventory;
    }
    if (clickContainer.stateId() < container.state()) {
      this.player.resendContainer();
      return true; // out of sync
    }

    final var finalContainer = container;
    this.server.eventHandler().call(new PlayerClickContainerEvent(
        this.player, container, clickContainer.carriedItem(), slot))
        .thenAcceptAsync(event -> {
          if (event.result().denied()) { // let's resend to override client prediction
            if (finalContainer == inventory) {
              this.player.inventory().resend();
            } else {
              this.player.resendContainer();
            }
          } else {
            // TODO: check, do not trust the client here!
            for (final var entry : clickContainer.slots().int2ObjectEntrySet()) {
              var key = entry.getIntKey();
              if (finalContainer != inventory) {
                if (key < finalContainer.type().size()) {
                  finalContainer.item(key, entry.getValue());
                  continue;
                } else {
                  key -= finalContainer.type().size() - 9;
                }
              }
              inventory.item0(key, entry.getValue(), false);
            }
          }
        }, this.connection.executor())
        .exceptionally(throwable -> {
          LOGGER.error("Exception while handling container click for {}", this.player.name(),
              throwable);
          return null;
        });
    return true;
  }

  @Override
  public boolean handle(CloseContainer closeContainer) {
    if (this.player.openedContainer() != null) {
      this.player.openedContainer().removeViewer(this.player);
      this.player.setContainer(null);
    }
    return true;
  }

  @Override
  public boolean handle(PluginMessage pluginMessage) {
    this.player.handlePluginMessage(pluginMessage);
    return true;
  }

  @Override
  public boolean handle(Interact interact) {
    final var entity = this.server.entity(interact.entityId());
    if (entity == null || entity.world() != this.player.world()) { // impossible
      return true;
    }
    if (interact.type() == 1) {
      this.server.eventHandler().justCall(new PlayerAttackedEntityEvent(this.player, entity));
    }
    return true;
  }

  @Override
  public boolean handle(KeepAlive keepAlive) {
    this.player.setKeepAlivePending(false);
    return true;
  }

  @Override
  public boolean handle(PlayerPosition playerPosition) {
    if (!this.player.receivedTeleportConfirmation()) {
      return true;
    }

    final var x = playerPosition.x();
    final var y = playerPosition.y();
    final var z = playerPosition.z();
    final var position = this.player.position();

    if (position.x() == x && position.y() == y && position.z() == z) {
      return true; // same location nothing to do
    }

    this.player.onGround = playerPosition.onGround();
    this.player.move(Position.position(x, y, z, position.yaw(), position.pitch()));
    return true;
  }

  @Override
  public boolean handle(PlayerPositionAndRotation playerPositionAndRotation) {
    if (!this.player.receivedTeleportConfirmation()) {
      return true;
    }

    final var x = playerPositionAndRotation.x();
    final var y = playerPositionAndRotation.y();
    final var z = playerPositionAndRotation.z();
    final var yaw = playerPositionAndRotation.yaw();
    final var pitch = playerPositionAndRotation.pitch();
    final var position = this.player.position();

    if (position.x() == x && position.y() == y && position.z() == z
        && position.yaw() == yaw && position.pitch() == pitch) {
      return true; // same location nothing to do
    }

    this.player.onGround = playerPositionAndRotation.onGround();
    this.player.move(Position.position(x, y, z, yaw, pitch));
    return true;
  }

  @Override
  public boolean handle(PlayerRotation playerRotation) {
    if (!this.player.receivedTeleportConfirmation()) {
      return true;
    }

    final var yaw = playerRotation.yaw();
    final var pitch = playerRotation.pitch();
    final var position = this.player.position();

    if (position.yaw() == yaw && position.pitch() == pitch) {
      return true; // same location nothing to do
    }

    this.player.onGround = playerRotation.onGround();
    this.player.move(Position.position(position.x(), position.y(), position.z(), yaw, pitch));
    return true;
  }

  @Override
  public boolean handle(PlayerOnGround playerOnGround) {
    this.player.onGround = playerOnGround.onGround();
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
        final var block = this.player.world().block(playerAction.position());
        if (this.player.instantBreak() || block.destroyTime() == 0.0F) {
          this.callBlockBreak(block, playerAction);
        } else {
          this.player.send(new BlockAcknowledge(playerAction.sequence()));
        }
      }
      case 1 -> { // cancelled digging

      }
      case 2 -> {
        final var block = this.player.world().block(playerAction.position());
        if (block.destroyTime() == -1) {
          return true; // impossible
        }

        this.callBlockBreak(block, playerAction); // finished digging
      }
      case 3 -> this.player.inventory()
          .item(this.player.heldItemSlot(), ItemStack.empty()); // drop stack
      case 4 -> { // drop item
        var itemInHand = this.player.inventory().itemInMainHand();
        if (!itemInHand.isEmpty()) {
          itemInHand = itemInHand.amount(itemInHand.amount() - 1);
          if (itemInHand.amount() < 1) {
            itemInHand = ItemStack.empty();
          }
          this.player.inventory().item(this.player.heldItemSlot(), itemInHand);
        }
      }
      case 5 -> { // shoot arrow / finish eating

      }
      case 6 -> { // swap item
        final var inventory = this.player.inventory();
        final var itemInMainHand = inventory.itemInMainHand();
        final var itemInOffHand = inventory.itemInOffHand();
        inventory.item(this.player.heldItemSlot(), itemInOffHand);
        inventory.itemInOffHand(itemInMainHand);
      }
    }
    return true;
  }

  private void callBlockBreak(final BlockState block, final PlayerAction playerAction) {
    this.server.eventHandler()
        .call(new BlockBreakEvent(this.player, playerAction.position(), block))
        .thenAcceptAsync(event -> {
          if (event.result().allowed()) {
            this.player.world().block(event.position(), Block.AIR);
          } else {
            this.player.send(new BlockAcknowledge(playerAction.sequence()));
          }
        }, this.connection.executor())
        .exceptionally(throwable -> {
          LOGGER.error("Exception while block break from {}", this.player.name(), throwable);
          return null;
        });
  }

  @Override
  public boolean handle(PlayerCommand playerCommand) {
    switch (playerCommand.action()) {
      case START_SNEAKING -> this.player.pose(Entity.Pose.SNEAKING);
      case STOP_SNEAKING -> this.player.pose(Entity.Pose.STANDING);
    }
    return true;
  }

  @Override
  public boolean handle(HeldItem heldItem) {
    this.player.heldItem = heldItem.slot();
    this.player.sendViewers(new Equipment(this.player.id(),
        OneInt2ObjectMap.of(0, this.player.inventory().item(heldItem.slot()))));
    return true;
  }

  @Override
  public boolean handle(CreativeModeSlot creativeModeSlot) {
    if (this.player.gameMode() != GameMode.CREATIVE) {
      LOGGER.info(this.player.name() + " tried to set a slot, but is not in creative mode.");
      return false;
    }
    final var slot = creativeModeSlot.slot();
    if (slot == -1) { // ignore dropping
      return true;
    }
    this.player.inventory().item0(creativeModeSlot.slot(), creativeModeSlot.clickedItem(), false);
    return true;
  }

  @Override
  public boolean handle(SwingArm swingArm) {
    this.player.sendViewers(
        new EntityAnimation(this.player.id(), (byte) (swingArm.hand() == 1 ? 3 : 0)));
    return true;
  }

  @Override
  public boolean handle(TeleportToEntity teleportToEntity) {
    if (this.player.gameMode() == GameMode.SPECTATOR) {
      final var target = this.server.player(teleportToEntity.target());
      if (target != null) {
        if (target.world() != this.player.world()) {
          this.player.world(target.world());
        }
        final var old = this.player.position();
        final var position = target.position();
        if (!old.equals(position)) {
          this.player.move(position);
        }
      }
    } else {
      LOGGER.info("{} tried to teleport, but is not in spectator mode.", this.player.name());
    }
    return true;
  }

  @Override
  public boolean handle(UseItemOn useItemOn) {
    final var inventory = this.player.inventory();
    final var slot = (useItemOn.hand() == 0 ? inventory.itemInMainHand()
        : inventory.itemInOffHand());
    this.server.eventHandler().call(new PlayerUseItemEvent(this.player, slot))
        .thenAcceptAsync(event -> {
          if (slot.isEmpty()) {
            return;
          }
          var position = this.calculatePosition(useItemOn.position(), useItemOn.face());
          if (this.player.openedContainer() != null) {
            this.player.send(new BlockAcknowledge(useItemOn.sequence()));
            if (useItemOn.hand() == 0) {
              inventory.itemInMainHand(slot);
            } else {
              inventory.itemInOffHand(slot);
            }
            return;
          }

          final var currentBlock = this.player.world().block(position);
          if (currentBlock != Block.AIR) {
            return; // block at position, don't set
          }
          var block = Block.get(slot.material().key().asString());
          if (block.hasProperty("facing")) { // let's set the correct facing
            final var rotation =
                (int) Math.floor(this.player.position().yaw() / 90.0D + 0.5D) & 3;
            block = block.property("facing", switch (rotation % 4) {
              case 0 -> SOUTH.toString();
              case 1 -> WEST.toString();
              case 2 -> NORTH.toString();
              case 3 -> EAST.toString();
              default -> null;
            });
          }
          this.server.eventHandler().call(new BlockPlaceEvent(this.player, position, block))
              .thenAcceptAsync(placeEvent -> {
                if (placeEvent.result().allowed()) {
                  this.player.world().block(placeEvent.position(), placeEvent.block());
                } else {
                  this.player.send(new BlockAcknowledge(useItemOn.sequence()));
                }
              }, this.connection.executor()).exceptionally(throwable -> {
                LOGGER.error("Exception while handling block place for " + this.player.name(),
                    throwable);
                return null;
              });
        }, this.connection.executor());
    return true;
  }

  @Override
  public boolean handle(UseItem useItem) {
    final var inventory = this.player.inventory();
    this.server.eventHandler().call(new PlayerUseItemEvent(this.player,
        (useItem.hand() == 0 ? inventory.itemInMainHand() : inventory.itemInOffHand())));
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
}

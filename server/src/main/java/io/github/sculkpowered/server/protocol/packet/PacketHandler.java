package io.github.sculkpowered.server.protocol.packet;

import io.github.sculkpowered.server.protocol.packet.config.FinishConfiguration;
import io.github.sculkpowered.server.protocol.packet.handshake.Handshake;
import io.github.sculkpowered.server.protocol.packet.login.EncryptionResponse;
import io.github.sculkpowered.server.protocol.packet.login.LoginAcknowledged;
import io.github.sculkpowered.server.protocol.packet.login.LoginPluginResponse;
import io.github.sculkpowered.server.protocol.packet.login.LoginStart;
import io.github.sculkpowered.server.protocol.packet.play.ChatMessage;
import io.github.sculkpowered.server.protocol.packet.play.ClientCommand;
import io.github.sculkpowered.server.protocol.packet.play.ClientInformation;
import io.github.sculkpowered.server.protocol.packet.play.ConfirmTeleportation;
import io.github.sculkpowered.server.protocol.packet.play.CreativeModeSlot;
import io.github.sculkpowered.server.protocol.packet.play.EditBook;
import io.github.sculkpowered.server.protocol.packet.play.HeldItem;
import io.github.sculkpowered.server.protocol.packet.play.Interact;
import io.github.sculkpowered.server.protocol.packet.play.KeepAlive;
import io.github.sculkpowered.server.protocol.packet.play.PlayerAbilities;
import io.github.sculkpowered.server.protocol.packet.play.PlayerAction;
import io.github.sculkpowered.server.protocol.packet.play.PlayerCommand;
import io.github.sculkpowered.server.protocol.packet.play.PlayerSession;
import io.github.sculkpowered.server.protocol.packet.play.PluginMessage;
import io.github.sculkpowered.server.protocol.packet.play.RecipeBookSettings;
import io.github.sculkpowered.server.protocol.packet.play.SwingArm;
import io.github.sculkpowered.server.protocol.packet.play.TeleportToEntity;
import io.github.sculkpowered.server.protocol.packet.play.UseItem;
import io.github.sculkpowered.server.protocol.packet.play.UseItemOn;
import io.github.sculkpowered.server.protocol.packet.play.command.ChatCommand;
import io.github.sculkpowered.server.protocol.packet.play.command.CommandSuggestionsRequest;
import io.github.sculkpowered.server.protocol.packet.play.container.ClickContainer;
import io.github.sculkpowered.server.protocol.packet.play.container.ClickContainerButton;
import io.github.sculkpowered.server.protocol.packet.play.container.CloseContainer;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerOnGround;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerPosition;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerPositionAndRotation;
import io.github.sculkpowered.server.protocol.packet.play.position.PlayerRotation;
import io.github.sculkpowered.server.protocol.packet.status.StatusPing;
import io.github.sculkpowered.server.protocol.packet.status.StatusRequest;

public abstract class PacketHandler {

  // Handshake
  public boolean handle(final Handshake handshake) {
    return false;
  }

  // Status
  public boolean handle(final StatusRequest statusRequest) {
    return false;
  }

  public boolean handle(final StatusPing statusPing) {
    return false;
  }

  // Login
  public boolean handle(final LoginStart loginStart) {
    return false;
  }

  public boolean handle(final EncryptionResponse encryptionResponse) {
    return false;
  }

  public boolean handle(final LoginPluginResponse pluginResponse) {
    return false;
  }

  public boolean handle(final LoginAcknowledged loginAcknowledged) {
    return false;
  }

  // Config
  public boolean handle(final FinishConfiguration finishConfiguration) {
    return false;
  }

  // Play
  public boolean handle(final ConfirmTeleportation confirmTeleportation) {
    return false;
  }

  public boolean handle(final ChatCommand chatCommand) {
    return false;
  }

  public boolean handle(final ChatMessage chatMessage) {
    return false;
  }

  public boolean handle(final PlayerSession playerSession) {
    return false;
  }

  public boolean handle(final ClientCommand clientCommand) {
    return false;
  }

  public boolean handle(final ClientInformation clientInformation) {
    return false;
  }

  public boolean handle(final CommandSuggestionsRequest commandSuggestionsRequest) {
    return false;
  }

  public boolean handle(final ClickContainerButton clickContainerButton) {
    return false;
  }

  public boolean handle(final ClickContainer clickContainer) {
    return false;
  }

  public boolean handle(final CloseContainer closeContainer) {
    return false;
  }

  public boolean handle(final PluginMessage pluginMessage) {
    return false;
  }

  public boolean handle(final EditBook editBook) {
    return false;
  }

  public boolean handle(final Interact interact) {
    return false;
  }

  public boolean handle(final KeepAlive keepAlive) {
    return false;
  }

  public boolean handle(final PlayerPosition playerPosition) {
    return false;
  }

  public boolean handle(final PlayerPositionAndRotation playerPositionAndRotation) {
    return false;
  }

  public boolean handle(final PlayerRotation playerRotation) {
    return false;
  }

  public boolean handle(final PlayerOnGround playerOnGround) {
    return false;
  }

  public boolean handle(final PlayerAbilities playerAbilities) {
    return false;
  }

  public boolean handle(final PlayerAction playerAction) {
    return false;
  }

  public boolean handle(final PlayerCommand playerCommand) {
    return false;
  }

  public boolean handle(final RecipeBookSettings recipeBookSettings) {
    return false;
  }

  public boolean handle(final HeldItem heldItem) {
    return false;
  }

  public boolean handle(final CreativeModeSlot creativeModeSlot) {
    return false;
  }

  public boolean handle(final SwingArm swingArm) {
    return false;
  }

  public boolean handle(final TeleportToEntity teleportToEntity) {
    return false;
  }

  public boolean handle(final UseItemOn useItemOn) {
    return false;
  }

  public boolean handle(final UseItem useItem) {
    return false;
  }
}

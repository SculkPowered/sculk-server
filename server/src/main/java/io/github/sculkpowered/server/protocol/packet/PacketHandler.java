package io.github.sculkpowered.server.protocol.packet;

import io.github.sculkpowered.server.protocol.packet.shared.FinishConfigurationPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.Intention;
import io.github.sculkpowered.server.protocol.packet.serverbound.KeyPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.LoginAcknowledgedPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.CustomQueryAnswerPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.HelloPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ChatPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ClientCommandPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ClientInformationPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.AcceptTeleportationPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.SetCreativeModeSlotPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.EditBookPacket;
import io.github.sculkpowered.server.protocol.packet.shared.CarriedItemPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.InteractPacket;
import io.github.sculkpowered.server.protocol.packet.shared.KeepAlivePacket;
import io.github.sculkpowered.server.protocol.packet.clientbound.PlayerAbilitiesPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.PlayerActionPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.PlayerCommandPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ChatSessionUpdatePacket;
import io.github.sculkpowered.server.protocol.packet.shared.CustomPayloadPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.RecipeBookChangeSettingsPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.SwingPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.TeleportToEntityPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.UseItemPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.UseItemOnPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ChatCommandPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.CommandSuggestionsPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ContainerClickPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.ContainerButtonClickPacket;
import io.github.sculkpowered.server.protocol.packet.shared.ContainerClosePacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.MovePlayerStatusOnlyPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.MovePlayerPosPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.MovePlayerPosRotPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.MovePlayerRotPacket;
import io.github.sculkpowered.server.protocol.packet.shared.StatusPingPacket;
import io.github.sculkpowered.server.protocol.packet.serverbound.StatusRequest;

public abstract class PacketHandler {

  // Handshake
  public boolean handle(final Intention intention) {
    return false;
  }

  // Status
  public boolean handle(final StatusRequest statusRequest) {
    return false;
  }

  public boolean handle(final StatusPingPacket statusPing) {
    return false;
  }

  // Login
  public boolean handle(final HelloPacket hello) {
    return false;
  }

  public boolean handle(final KeyPacket key) {
    return false;
  }

  public boolean handle(final CustomQueryAnswerPacket pluginResponse) {
    return false;
  }

  public boolean handle(final LoginAcknowledgedPacket loginAcknowledged) {
    return false;
  }

  // Config
  public boolean handle(final FinishConfigurationPacket finishConfiguration) {
    return false;
  }

  // Play
  public boolean handle(final AcceptTeleportationPacket confirmTeleportation) {
    return false;
  }

  public boolean handle(final ChatCommandPacket chatCommand) {
    return false;
  }

  public boolean handle(final ChatPacket chatMessage) {
    return false;
  }

  public boolean handle(final ChatSessionUpdatePacket playerSession) {
    return false;
  }

  public boolean handle(final ClientCommandPacket clientCommand) {
    return false;
  }

  public boolean handle(final ClientInformationPacket clientInformation) {
    return false;
  }

  public boolean handle(final CommandSuggestionsPacket commandSuggestionsRequest) {
    return false;
  }

  public boolean handle(final ContainerButtonClickPacket clickContainerButton) {
    return false;
  }

  public boolean handle(final ContainerClickPacket clickContainer) {
    return false;
  }

  public boolean handle(final ContainerClosePacket closeContainer) {
    return false;
  }

  public boolean handle(final CustomPayloadPacket customPayload) {
    return false;
  }

  public boolean handle(final EditBookPacket editBook) {
    return false;
  }

  public boolean handle(final InteractPacket interact) {
    return false;
  }

  public boolean handle(final KeepAlivePacket keepAlive) {
    return false;
  }

  public boolean handle(final MovePlayerPosPacket playerPosition) {
    return false;
  }

  public boolean handle(final MovePlayerPosRotPacket playerPositionAndRotation) {
    return false;
  }

  public boolean handle(final MovePlayerRotPacket playerRotation) {
    return false;
  }

  public boolean handle(final MovePlayerStatusOnlyPacket playerOnGround) {
    return false;
  }

  public boolean handle(final PlayerAbilitiesPacket playerAbilities) {
    return false;
  }

  public boolean handle(final PlayerActionPacket playerAction) {
    return false;
  }

  public boolean handle(final PlayerCommandPacket playerCommand) {
    return false;
  }

  public boolean handle(final RecipeBookChangeSettingsPacket recipeBookSettings) {
    return false;
  }

  public boolean handle(final CarriedItemPacket heldItem) {
    return false;
  }

  public boolean handle(final SetCreativeModeSlotPacket creativeModeSlot) {
    return false;
  }

  public boolean handle(final SwingPacket swingArm) {
    return false;
  }

  public boolean handle(final TeleportToEntityPacket teleportToEntity) {
    return false;
  }

  public boolean handle(final UseItemOnPacket useItemOn) {
    return false;
  }

  public boolean handle(final UseItemPacket useItem) {
    return false;
  }
}

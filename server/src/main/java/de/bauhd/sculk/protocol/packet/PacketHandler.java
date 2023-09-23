package de.bauhd.sculk.protocol.packet;

import de.bauhd.sculk.protocol.packet.config.FinishConfiguration;
import de.bauhd.sculk.protocol.packet.handshake.Handshake;
import de.bauhd.sculk.protocol.packet.login.EncryptionResponse;
import de.bauhd.sculk.protocol.packet.login.LoginAcknowledged;
import de.bauhd.sculk.protocol.packet.login.LoginPluginResponse;
import de.bauhd.sculk.protocol.packet.login.LoginStart;
import de.bauhd.sculk.protocol.packet.play.*;
import de.bauhd.sculk.protocol.packet.play.command.ChatCommand;
import de.bauhd.sculk.protocol.packet.play.container.ClickContainer;
import de.bauhd.sculk.protocol.packet.play.container.ClickContainerButton;
import de.bauhd.sculk.protocol.packet.play.container.CloseContainer;
import de.bauhd.sculk.protocol.packet.play.position.PlayerOnGround;
import de.bauhd.sculk.protocol.packet.play.position.PlayerPosition;
import de.bauhd.sculk.protocol.packet.play.position.PlayerPositionAndRotation;
import de.bauhd.sculk.protocol.packet.play.position.PlayerRotation;
import de.bauhd.sculk.protocol.packet.status.StatusPing;
import de.bauhd.sculk.protocol.packet.status.StatusRequest;

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

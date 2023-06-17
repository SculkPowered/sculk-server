package de.bauhd.minecraft.server.protocol.packet.login;

import de.bauhd.minecraft.server.entity.player.GameProfile;
import de.bauhd.minecraft.server.protocol.Buffer;
import de.bauhd.minecraft.server.protocol.packet.Packet;

import java.util.UUID;

public final class LoginSuccess implements Packet {

    private final GameProfile profile;

    public LoginSuccess(GameProfile profile) {
        this.profile = profile;
    }

    @Override
    public void encode(Buffer buf) {
        buf
                .writeUniqueId(this.profile.uniqueId())
                .writeString(this.profile.name())
                .writeVarInt(this.profile.properties().size());
        for (final var property : profile.properties()) {
            buf.writeString(property.key()).writeString(property.value());
            if (property.signature() != null) {
                buf.writeBoolean(true);
                buf.writeString(property.signature());
            } else {
                buf.writeBoolean(false);
            }
        }
    }

    @Override
    public String toString() {
        return "LoginSuccess{" +
                "profile=" + this.profile +
                '}';
    }
}

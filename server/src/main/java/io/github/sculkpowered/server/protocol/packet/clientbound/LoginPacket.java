package io.github.sculkpowered.server.protocol.packet.clientbound;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.world.dimension.Dimension;

public final class LoginPacket implements ClientboundPacket {

  private final int entityId;
  private final byte gameMode;
  private final Dimension dimensionType;

  public LoginPacket(final int entityId, final byte gameMode, final Dimension dimensionType) {
    this.entityId = entityId;
    this.gameMode = gameMode;
    this.dimensionType = dimensionType;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeInt(this.entityId) // Entity id
        .writeBoolean(false) // Hardcode
        .writeVarInt(1) // Dimensions
        .writeString("minecraft:world") // Dimensions
        .writeVarInt(20) // Max Players
        .writeVarInt(18) // View Distance
        .writeVarInt(10) // Simulation Distance
        .writeBoolean(false) // Reduced Debug Info
        .writeBoolean(false) // Enable respawn screen
        .writeBoolean(false) // Limited crafting
        .writeVarInt(this.dimensionType.id()) // Dimension Type
        .writeString("minecraft:overworld") // Dimension Name
        .writeLong(0) // Hashed Seed
        .writeByte(this.gameMode) // GameMode
        .writeByte((byte) -1) // Previous GameMode
        .writeBoolean(false) // Debug
        .writeBoolean(true) // Flat
        .writeBoolean(false) // Death Location
        .writeVarInt(0) // Portal Cooldown
        .writeBoolean(false); // Secure Chat
  }

  @Override
  public String toString() {
    return "LoginPacket{" +
        "entityId=" + this.entityId +
        '}';
  }
}

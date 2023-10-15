package de.bauhd.sculk.protocol.packet.play;

import de.bauhd.sculk.damage.DamageType;
import de.bauhd.sculk.protocol.Buffer;
import de.bauhd.sculk.protocol.packet.Packet;
import de.bauhd.sculk.registry.Registry;
import de.bauhd.sculk.world.biome.Biome;
import de.bauhd.sculk.world.dimension.Dimension;
import net.kyori.adventure.nbt.CompoundBinaryTag;

public final class Login implements Packet {

  private final int entityId;
  private final byte gameMode;
  private final Registry<Biome> biomeRegistry;
  private final Registry<Dimension> dimensionRegistry;
  private final Registry<DamageType> damageTypeRegistry;
  private final String dimensionType;

  public Login(final int entityId, final byte gameMode, final Registry<Biome> biomeRegistry,
      final Registry<Dimension> dimensionRegistry, final Registry<DamageType> damageTypeRegistry,
      final String dimensionType) {
    this.entityId = entityId;
    this.biomeRegistry = biomeRegistry;
    this.dimensionRegistry = dimensionRegistry;
    this.damageTypeRegistry = damageTypeRegistry;
    this.gameMode = gameMode;
    this.dimensionType = dimensionType;
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeInt(this.entityId) // Entity id
        .writeBoolean(false) // Hardcode
        .writeByte(this.gameMode) // GameMode
        .writeByte((byte) -1) // Previous GameMode
        .writeVarInt(1) // Dimensions
        .writeString("minecraft:world") // Dimensions
        .writeCompoundTag(CompoundBinaryTag.builder()
            .put(this.biomeRegistry.type(), this.biomeRegistry.asNBT())
            .put(this.dimensionRegistry.type(), this.dimensionRegistry.asNBT())
            .put(this.damageTypeRegistry.type(), this.damageTypeRegistry.asNBT())
            .build())
        .writeString(this.dimensionType) // Dimension Type
        .writeString("minecraft:overworld") // Dimension Name
        .writeLong(0) // Hashed Seed
        .writeVarInt(20) // Max Players
        .writeVarInt(18) // View Distance
        .writeVarInt(10) // Simulation Distance
        .writeBoolean(false) // Reduced Debug Info
        .writeBoolean(false) // Enable respawn screen
        .writeBoolean(false) // Debug
        .writeBoolean(true) // Flat
        .writeBoolean(false) // Death Location
        .writeVarInt(0); // Portal Cooldown
  }

  @Override
  public String toString() {
    return "Login{" +
        "entityId=" + this.entityId +
        '}';
  }
}

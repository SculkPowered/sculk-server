package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import eu.sculkpowered.server.attribute.Attribute;
import eu.sculkpowered.server.attribute.AttributeValue;
import eu.sculkpowered.server.attribute.SculkAttributeValue;
import eu.sculkpowered.server.container.equipment.EntityEquipment;
import eu.sculkpowered.server.container.equipment.SculkEquipment;
import eu.sculkpowered.server.entity.player.Player;
import eu.sculkpowered.server.entity.player.SculkPlayer;
import eu.sculkpowered.server.protocol.packet.clientbound.SetEquipmentPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.UpdateAttributesPacket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractLivingEntity extends AbstractEntity implements LivingEntity {

  private final Map<Attribute, SculkAttributeValue> attributes = new HashMap<>();
  protected EntityEquipment equipment;

  @Override
  public @NotNull AttributeValue attribute(@NotNull Attribute attribute) {
    return this.attributes.computeIfAbsent(attribute, attr -> new SculkAttributeValue(attr, this::attributeChange));
  }

  public AbstractLivingEntity(final SculkServer server) {
    super(server);
    this.equipment = new SculkEquipment(this);
  }

  public AbstractLivingEntity(final SculkServer server, final UUID uuid) {
    super(server, uuid);
  }

  @Override
  public boolean addViewer(@NotNull Player player) {
    final var added = super.addViewer(player);
    if (added) {
      final var sculkPlayer = (SculkPlayer) player;
      if (!this.attributes.isEmpty()) {
        sculkPlayer.send(new UpdateAttributesPacket(this.id, this.attributes.values()));
      }
      final var equipment = this.equipment.asMap();
      if (!equipment.isEmpty()) {
        sculkPlayer.send(new SetEquipmentPacket(this.id, equipment));
      }
    }
    return added;
  }

  @Override
  public float health() {
    return this.metadata.getFloat(9, 1);
  }

  @Override
  public void health(float health) {
    this.metadata.setFloat(9, health);
  }

  @Override
  public int potionEffectColor() {
    return this.metadata.getVarInt(10, 0);
  }

  @Override
  public void potionEffectColor(int effectColor) {
    this.metadata.setVarInt(10, effectColor);
  }

  @Override
  public boolean isPotionEffectAmbient() {
    return this.metadata.getBoolean(11, false);
  }

  @Override
  public void potionEffectAmbient(boolean ambient) {
    this.metadata.setBoolean(11, ambient);
  }

  @Override
  public int numberOfArrows() {
    return this.metadata.getVarInt(12, 0);
  }

  @Override
  public void numberOfArrows(int arrows) {
    this.metadata.setVarInt(12, arrows);
  }

  @Override
  public int numberOfBeeStingers() {
    return this.metadata.getVarInt(13, 0);
  }

  @Override
  public void numberOfBeeStingers(int beeStingers) {
    this.metadata.setVarInt(13, beeStingers);
  }

  @Override
  public @NotNull EntityEquipment equipment() {
    return this.equipment;
  }

  protected void attributeChange(SculkAttributeValue value) {
    this.sendViewers(new UpdateAttributesPacket(this.id, List.of(value)));
  }
}

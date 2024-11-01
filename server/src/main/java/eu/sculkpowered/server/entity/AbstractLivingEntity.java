package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.attribute.Attribute;
import eu.sculkpowered.server.attribute.AttributeValue;
import eu.sculkpowered.server.attribute.SculkAttributeValue;
import eu.sculkpowered.server.container.equipment.EntityEquipment;
import eu.sculkpowered.server.entity.meta.EntityMeta;
import eu.sculkpowered.server.entity.player.Player;
import eu.sculkpowered.server.entity.player.SculkPlayer;
import eu.sculkpowered.server.protocol.packet.clientbound.SetEquipmentPacket;
import eu.sculkpowered.server.protocol.packet.clientbound.UpdateAttributesPacket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractLivingEntity<M extends EntityMeta> extends AbstractEntity<M> implements LivingEntity<M> {

  private final Map<Attribute, SculkAttributeValue> attributes = new HashMap<>();
  protected EntityEquipment equipment;

  public AbstractLivingEntity(final EntityType<Entity<M>> type) {
    super(type);
  }

  public AbstractLivingEntity(final EntityType<Entity<M>> type, final UUID uniqueId) {
    super(type, uniqueId);
  }

  @Override
  public @NotNull AttributeValue attribute(@NotNull Attribute attribute) {
    return this.attributes.computeIfAbsent(attribute, attr -> new SculkAttributeValue(attr, this::attributeChange));
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
  public @NotNull EntityEquipment equipment() {
    return this.equipment;
  }

  protected void attributeChange(SculkAttributeValue value) {
    this.sendViewers(new UpdateAttributesPacket(this.id, List.of(value)));
  }
}

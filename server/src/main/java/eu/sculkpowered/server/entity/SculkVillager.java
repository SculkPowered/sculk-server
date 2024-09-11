package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public class SculkVillager extends AbstractAnimal implements Villager {

  public SculkVillager(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.VILLAGER;
  }
}

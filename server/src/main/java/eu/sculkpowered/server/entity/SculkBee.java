package eu.sculkpowered.server.entity;

import eu.sculkpowered.server.SculkServer;
import org.jetbrains.annotations.NotNull;

public final class SculkBee extends AbstractAnimal implements Bee {

  public SculkBee(final SculkServer server) {
    super(server);
  }

  @Override
  public @NotNull EntityType type() {
    return EntityType.BEE;
  }

  @Override
  public boolean angry() {
    return this.metadata.inMask(17, 0x02);
  }

  @Override
  public void angry(boolean angry) {
    this.metadata.setMask(17, 0x02, angry);
  }

  @Override
  public boolean stung() {
    return this.metadata.inMask(17, 0x04);
  }

  @Override
  public void stung(boolean stung) {
    this.metadata.setMask(17, 0x04, stung);
  }

  @Override
  public boolean nectar() {
    return this.metadata.inMask(17, 0x08);
  }

  @Override
  public void nectar(boolean nectar) {
    this.metadata.setMask(17, 0x08, nectar);
  }
}

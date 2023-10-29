package io.github.sculkpowered.server.entity;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class SculkTextDisplay extends AbstractDisplay implements TextDisplay {

  @Override
  public @NotNull EntityType type() {
    return EntityType.TEXT_DISPLAY;
  }

  @Override
  public @NotNull Component getText() {
    return this.metadata.getComponent(22, Component.empty());
  }

  @Override
  public void setText(@NotNull Component text) {
    this.metadata.setComponent(22, text);
  }

  @Override
  public int getLineWidth() {
    return this.metadata.getVarInt(23, 200);
  }

  @Override
  public void setLineWidth(int lineWidth) {
    this.metadata.setVarInt(23, lineWidth);
  }

  @Override
  public int getBackgroundColor() {
    return this.metadata.getVarInt(24, 0x40000000);
  }

  @Override
  public void setBackgroundColor(int backgroundColor) {
    this.metadata.setVarInt(24, backgroundColor);
  }

  @Override
  public byte getTextOpacity() {
    return this.metadata.getByte(25, (byte) -1);
  }

  @Override
  public void setTextOpacity(byte opacity) {
    this.metadata.setByte(25, opacity);
  }

  @Override
  public boolean hasShadow() {
    return this.metadata.inMask(26, 0x01);
  }

  @Override
  public void setShadow(boolean shadow) {
    this.metadata.setMask(26, 0x01, shadow);
  }

  @Override
  public boolean isSeeThrough() {
    return this.metadata.inMask(26, 0x02);
  }

  @Override
  public void setSeeThrough(boolean seeThrough) {
    this.metadata.setMask(26, 0x02, seeThrough);
  }
}

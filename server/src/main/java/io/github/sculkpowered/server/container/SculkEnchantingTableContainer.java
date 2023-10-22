package io.github.sculkpowered.server.container;

import io.github.sculkpowered.server.enchantment.Enchantment;
import io.github.sculkpowered.server.entity.player.SculkPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

public final class SculkEnchantingTableContainer extends SculkContainer implements
    EnchantmentTableContainer {

  private int requirementForTopEnchanting;
  private int requirementForMiddleEnchanting;
  private int requirementForBottomEnchanting;
  private int enchantmentSeed;
  private Enchantment topEnchantment;
  private Enchantment middleEnchantment;
  private Enchantment bottomEnchantment;
  private int topEnchantingLevel;
  private int middleEnchantingLevel;
  private int bottomEnchantingLevel;

  public SculkEnchantingTableContainer(final Component title) {
    super(title);
  }

  @Override
  public void setRequirementForTopEnchantment(int requirement) {
    this.sendProperty(0, requirement);
    this.requirementForTopEnchanting = requirement;
  }

  @Override
  public int getRequirementForTopEnchantment() {
    return this.requirementForTopEnchanting;
  }

  @Override
  public void setRequirementForMiddleEnchantment(int requirement) {
    this.sendProperty(1, requirement);
    this.requirementForMiddleEnchanting = requirement;
  }

  @Override
  public int getRequirementForMiddleEnchantment() {
    return this.requirementForMiddleEnchanting;
  }

  @Override
  public void setRequirementForBottomEnchantment(int requirement) {
    this.sendProperty(2, requirement);
    this.requirementForBottomEnchanting = requirement;
  }

  @Override
  public int getRequirementForBottomEnchantment() {
    return this.requirementForBottomEnchanting;
  }

  @Override
  public void setEnchantmentSeed(int seed) {
    this.sendProperty(3, seed);
    this.enchantmentSeed = seed;
  }

  @Override
  public int getEnchantmentSeed() {
    return this.enchantmentSeed;
  }

  @Override
  public void setTopEnchantment(@NotNull Enchantment enchantment) {
    this.sendProperty(4, enchantment.ordinal());
    this.topEnchantment = enchantment;
  }

  @Override
  public @NotNull Enchantment getTopEnchantment() {
    return this.topEnchantment;
  }

  @Override
  public void setMiddleEnchantment(@NotNull Enchantment enchantment) {
    this.sendProperty(5, enchantment.ordinal());
    this.middleEnchantment = enchantment;
  }

  @Override
  public @NotNull Enchantment getMiddleEnchantment() {
    return this.middleEnchantment;
  }

  @Override
  public void setBottomEnchantment(@NotNull Enchantment enchantment) {
    this.sendProperty(6, enchantment.ordinal());
    this.bottomEnchantment = enchantment;
  }

  @Override
  public @NotNull Enchantment getBottomEnchantment() {
    return this.bottomEnchantment;
  }

  @Override
  public void setTopEnchantingLevel(int level) {
    this.sendProperty(7, level);
    this.topEnchantingLevel = level;
  }

  @Override
  public int getTopEnchantingLevel() {
    return this.topEnchantingLevel;
  }

  @Override
  public void setMiddleEnchantingLevel(int level) {
    this.sendProperty(8, level);
    this.middleEnchantingLevel = level;
  }

  @Override
  public int getMiddleEnchantingLevel() {
    return this.middleEnchantingLevel;
  }

  @Override
  public void setBottomEnchantingLevel(int level) {
    this.sendProperty(9, level);
    this.bottomEnchantingLevel = level;
  }

  @Override
  public int getBottomEnchantingLevel() {
    return this.bottomEnchantingLevel;
  }

  @Override
  public @NotNull Type getType() {
    return Type.ENCHANTMENT;
  }

  @Override
  public void sendProperties(SculkPlayer player) {
    player.send(this.property(0, this.requirementForTopEnchanting));
    player.send(this.property(1, this.requirementForMiddleEnchanting));
    player.send(this.property(2, this.requirementForBottomEnchanting));
    player.send(this.property(3, this.enchantmentSeed));
    player.send(this.property(4, this.topEnchantment.ordinal()));
    player.send(this.property(5, this.middleEnchantment.ordinal()));
    player.send(this.property(6, this.bottomEnchantment.ordinal()));
    player.send(this.property(7, this.topEnchantingLevel));
    player.send(this.property(8, this.middleEnchantingLevel));
    player.send(this.property(9, this.bottomEnchantingLevel));
  }
}

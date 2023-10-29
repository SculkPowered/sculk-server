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
  public void requirementForTopEnchantment(int requirement) {
    this.sendProperty(0, requirement);
    this.requirementForTopEnchanting = requirement;
  }

  @Override
  public int requirementForTopEnchantment() {
    return this.requirementForTopEnchanting;
  }

  @Override
  public void requirementForMiddleEnchantment(int requirement) {
    this.sendProperty(1, requirement);
    this.requirementForMiddleEnchanting = requirement;
  }

  @Override
  public int requirementForMiddleEnchantment() {
    return this.requirementForMiddleEnchanting;
  }

  @Override
  public void requirementForBottomEnchantment(int requirement) {
    this.sendProperty(2, requirement);
    this.requirementForBottomEnchanting = requirement;
  }

  @Override
  public int requirementForBottomEnchantment() {
    return this.requirementForBottomEnchanting;
  }

  @Override
  public void enchantmentSeed(int seed) {
    this.sendProperty(3, seed);
    this.enchantmentSeed = seed;
  }

  @Override
  public int enchantmentSeed() {
    return this.enchantmentSeed;
  }

  @Override
  public void topEnchantment(@NotNull Enchantment enchantment) {
    this.sendProperty(4, enchantment.ordinal());
    this.topEnchantment = enchantment;
  }

  @Override
  public @NotNull Enchantment topEnchantment() {
    return this.topEnchantment;
  }

  @Override
  public void middleEnchantment(@NotNull Enchantment enchantment) {
    this.sendProperty(5, enchantment.ordinal());
    this.middleEnchantment = enchantment;
  }

  @Override
  public @NotNull Enchantment middleEnchantment() {
    return this.middleEnchantment;
  }

  @Override
  public void bottomEnchantment(@NotNull Enchantment enchantment) {
    this.sendProperty(6, enchantment.ordinal());
    this.bottomEnchantment = enchantment;
  }

  @Override
  public @NotNull Enchantment bottomEnchantment() {
    return this.bottomEnchantment;
  }

  @Override
  public void topEnchantingLevel(int level) {
    this.sendProperty(7, level);
    this.topEnchantingLevel = level;
  }

  @Override
  public int topEnchantingLevel() {
    return this.topEnchantingLevel;
  }

  @Override
  public void middleEnchantingLevel(int level) {
    this.sendProperty(8, level);
    this.middleEnchantingLevel = level;
  }

  @Override
  public int middleEnchantingLevel() {
    return this.middleEnchantingLevel;
  }

  @Override
  public void bottomEnchantingLevel(int level) {
    this.sendProperty(9, level);
    this.bottomEnchantingLevel = level;
  }

  @Override
  public int bottomEnchantingLevel() {
    return this.bottomEnchantingLevel;
  }

  @Override
  public @NotNull Type type() {
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

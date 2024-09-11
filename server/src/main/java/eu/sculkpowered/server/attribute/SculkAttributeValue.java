package eu.sculkpowered.server.attribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import org.jetbrains.annotations.NotNull;

public final class SculkAttributeValue implements AttributeValue {

  private final Attribute attribute;
  private final Map<UUID, AttributeModifier> modifiers;
  private final Consumer<SculkAttributeValue> consumer;
  private double baseValue;
  private double calculatedValue;

  public SculkAttributeValue(
      final Attribute attribute, final Consumer<SculkAttributeValue> consumer
  ) {
    this.attribute = attribute;
    this.modifiers = new HashMap<>();
    this.consumer = consumer;
    this.baseValue = this.attribute.def();
    this.calculatedValue = this.baseValue;
  }

  public Attribute attribute() {
    return this.attribute;
  }

  @Override
  public void baseValue(final double value) {
    this.baseValue = value;
    this.calculateValue();
  }

  @Override
  public double baseValue() {
    return this.baseValue;
  }

  @Override
  public void addModifier(@NotNull AttributeModifier modifier) {
    this.modifiers.put(modifier.uniqueId(), modifier);
    this.calculateValue();
  }

  @Override
  public void removeModifier(@NotNull AttributeModifier modifier) {
    if (this.modifiers.remove(modifier.uniqueId(), modifier)) {
      this.calculateValue();
    }
  }

  @Override
  public double calculatedValue() {
    return this.calculatedValue;
  }

  @Override
  public String toString() {
    return "SculkAttributeValue{" +
        "attribute=" + this.attribute +
        ", modifiers=" + this.modifiers +
        ", baseValue=" + this.baseValue +
        ", calculatedValue=" + this.calculatedValue +
        '}';
  }

  public Collection<AttributeModifier> modifiers() {
    return this.modifiers.values();
  }

  private void calculateValue() {
    var value = this.baseValue;
    final var modifiers = new ArrayList<>(this.modifiers.values());
    modifiers.sort(Comparator.comparing(AttributeModifier::operation));
    for (final var modifier : modifiers) {
      switch (modifier.operation()) {
        case ADD_VALUE -> value += modifier.amount();
        case MULTIPLY_BASE -> value += this.baseValue * modifier.amount();
        case MULTIPLY -> value *= 1.0F + modifier.amount();
      }
    }
    this.calculatedValue = Math.clamp(value, this.attribute.min(), this.attribute.max());
    this.consumer.accept(this);
  }
}

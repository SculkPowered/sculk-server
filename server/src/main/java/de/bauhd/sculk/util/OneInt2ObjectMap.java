package de.bauhd.sculk.util;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public final class OneInt2ObjectMap<V> implements Int2ObjectMap<V> {

  private final int key;
  private final V value;

  private OneInt2ObjectMap(final int key, final V value) {
    this.key = key;
    this.value = value;
  }

  public int getKey() {
    return this.key;
  }

  public V getValue() {
    return this.value;
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean containsValue(Object value) {
    return false;
  }

  @Override
  public void putAll(@NotNull Map<? extends Integer, ? extends V> m) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void defaultReturnValue(V rv) {
    throw new UnsupportedOperationException();
  }

  @Override
  public V defaultReturnValue() {
    return null;
  }

  @Override
  public ObjectSet<Entry<V>> int2ObjectEntrySet() {
    throw new UnsupportedOperationException();
  }

  @Override
  public @NotNull IntSet keySet() {
    throw new UnsupportedOperationException();
  }

  @Override
  public @NotNull ObjectCollection<V> values() {
    throw new UnsupportedOperationException();
  }

  @Override
  public V get(int key) {
    return this.key == key ? this.value : null;
  }

  @Override
  public boolean containsKey(int key) {
    return this.key == key;
  }

  public static <V> Int2ObjectMap<V> of(final int key, final V value) {
    return new OneInt2ObjectMap<>(key, value);
  }
}

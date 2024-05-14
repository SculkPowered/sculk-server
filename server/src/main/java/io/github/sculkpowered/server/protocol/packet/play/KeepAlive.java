package io.github.sculkpowered.server.protocol.packet.play;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.Packet;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import java.util.function.Supplier;

public final class KeepAlive implements Packet {

  public static final Supplier<KeepAlive> SUPPLIER = KeepAlive::new;

  private long timeMillis;

  public KeepAlive(final long timeMillis) {
    this.timeMillis = timeMillis;
  }

  private KeepAlive() {}

  @Override
  public void decode(Buffer buf) {
    this.timeMillis = buf.readLong();
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeLong(this.timeMillis);
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return Long.BYTES;
  }

  @Override
  public int maxLength() {
    return Long.BYTES;
  }

  @Override
  public String toString() {
    return "KeepAlive{" +
        "timeMillis=" + this.timeMillis +
        '}';
  }

  public long timeMillis() {
    return this.timeMillis;
  }
}

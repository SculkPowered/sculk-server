package eu.sculkpowered.server.protocol.packet.shared;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;
import java.util.function.Supplier;

public final class KeepAlivePacket implements ServerboundPacket, ClientboundPacket {

  public static final Supplier<KeepAlivePacket> SUPPLIER = KeepAlivePacket::new;

  private long timeMillis;

  public KeepAlivePacket(final long timeMillis) {
    this.timeMillis = timeMillis;
  }

  private KeepAlivePacket() {}

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
    return "KeepAlivePacket{" +
        "timeMillis=" + this.timeMillis +
        '}';
  }

  public long timeMillis() {
    return this.timeMillis;
  }
}

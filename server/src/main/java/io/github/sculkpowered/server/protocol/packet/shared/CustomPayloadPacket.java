package io.github.sculkpowered.server.protocol.packet.shared;

import io.github.sculkpowered.server.protocol.Buffer;
import io.github.sculkpowered.server.protocol.packet.ClientboundPacket;
import io.github.sculkpowered.server.protocol.packet.PacketHandler;
import io.github.sculkpowered.server.protocol.packet.ServerboundPacket;
import java.util.Arrays;
import java.util.function.Supplier;

public final class CustomPayloadPacket implements ServerboundPacket, ClientboundPacket {

  public static final Supplier<CustomPayloadPacket> SUPPLIER = CustomPayloadPacket::new;

  private String identifier;
  private byte[] data;

  public CustomPayloadPacket(final String identifier, final byte[] data) {
    this.identifier = identifier;
    this.data = data;
  }

  private CustomPayloadPacket() {}

  @Override
  public void decode(Buffer buf) {
    this.identifier = buf.readString(32);
    this.data = buf.readAll();
  }

  @Override
  public void encode(Buffer buf) {
    buf
        .writeString(this.identifier)
        .writeBytes(this.data);
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return 0;
  }

  @Override
  public String toString() {
    return "CustomPayloadPacket{" +
        "identifier='" + this.identifier + '\'' +
        ", data=" + Arrays.toString(this.data) +
        '}';
  }

  public String identifier() {
    return this.identifier;
  }

  public byte[] data() {
    return this.data;
  }
}

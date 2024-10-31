package eu.sculkpowered.server.protocol.packet.shared;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.ClientboundPacket;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;
import java.util.ArrayList;
import java.util.List;
import net.kyori.adventure.key.Key;

public final class SelectKnownPacks implements ServerboundPacket, ClientboundPacket {

  private List<Pack> knownPacks;

  public SelectKnownPacks(final List<Pack> knownPacks) {
    this.knownPacks = knownPacks;
  }

  public SelectKnownPacks() {
  }

  @Override
  public void encode(Buffer buf) {
    buf.writeVarInt(this.knownPacks.size());
    for (final var pack : this.knownPacks) {
      buf.writeString(pack.key.namespace());
      buf.writeString(pack.key.value());
      buf.writeString(pack.version);
    }
  }

  @Override
  public void decode(Buffer buf) {
    final var size = buf.readVarInt();
    this.knownPacks = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      this.knownPacks.add(new Pack(Key.key(buf.readString(), buf.readString()), buf.readString()));
    }
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  @Override
  public int minLength() {
    return Byte.BYTES;
  }

  public List<Pack> knownPacks() {
    return this.knownPacks;
  }

  public static final class Pack {
    private final Key key;
    private final String version;

    public Pack(final Key key, final String version) {
      this.key = key;
      this.version = version;
    }

    @Override
    public String toString() {
      return "Pack{" +
          "key=" + this.key +
          ", version='" + this.version + '\'' +
          '}';
    }
  }
}

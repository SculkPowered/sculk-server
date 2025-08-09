package eu.sculkpowered.server.protocol.packet.serverbound;

import eu.sculkpowered.server.protocol.Buffer;
import eu.sculkpowered.server.protocol.packet.PacketHandler;
import eu.sculkpowered.server.protocol.packet.ServerboundPacket;

public final class IntentionPacket implements ServerboundPacket {

  private int version;
  private String serverAddress;
  private int port;
  private int intent;

  @Override
  public void decode(Buffer buf) {
    this.version = buf.readVarInt();
    this.serverAddress = buf.readString();
    this.port = buf.readUnsignedShort();
    this.intent = buf.readVarInt();
  }

  @Override
  public boolean handle(PacketHandler handler) {
    return handler.handle(this);
  }

  public int version() {
    return this.version;
  }

  public String serverAddress() {
    return this.serverAddress;
  }

  public int port() {
    return this.port;
  }

  public int intent() {
    return this.intent;
  }

  @Override
  public int minLength() {
    return 5; // VarInt 1 + String 1 + Unsigned Short 2 + VarInt 1
  }

  @Override
  public int maxLength() {
    //if (AdvancedMinecraftServer.getInstance().getConfiguration().mode() == MinecraftConfig.Mode.BUNGEECORD) {
    return 32770; // max length with bungeecord
    //}
    //return 1039; // VarInt 5 + String 1027 + Unsigned Short 2 + VarInt 5
  }

  @Override
  public String toString() {
    return "HandshakePacket{" +
        "version=" + this.version +
        ", serverAddress='" + this.serverAddress + '\'' +
        ", port=" + this.port +
        ", intent=" + this.intent +
        '}';
  }
}

package io.github.sculkpowered.server.protocol;

import io.github.sculkpowered.server.protocol.State.PacketRegistry;

public final class Protocol {

  public static final String VERSION_NAME = "1.20.3/4";
  public static final int VERSION_PROTOCOL = 765;

  public static abstract class Direction {

    public static final Direction SERVERBOUND = new Direction() {
      @Override
      public PacketRegistry getRegistry(State state) {
        return state.serverBound;
      }
    };
    public static final Direction CLIENTBOUND = new Direction() {
      @Override
      public PacketRegistry getRegistry(State state) {
        return state.clientBound;
      }
    };

    public abstract State.PacketRegistry getRegistry(final State state);
  }
}

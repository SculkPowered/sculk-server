package de.bauhd.sculk.protocol;

public final class Protocol {

    public static final String VERSION_NAME = "1.20";
    public static final int VERSION_PROTOCOL = 763;

    public enum Direction {
        SERVERBOUND,
        CLIENTBOUND;

        public State.PacketRegistry getRegistry(final State state) {
            return (this == SERVERBOUND ? state.serverBound : state.clientBound);
        }
    }
}

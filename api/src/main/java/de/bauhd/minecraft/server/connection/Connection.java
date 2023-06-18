package de.bauhd.minecraft.server.connection;

import java.net.SocketAddress;

/**
 * Represents a connection to the server.
 */
public interface Connection {

    int getProtocolVersion();

    SocketAddress getAddress();
}

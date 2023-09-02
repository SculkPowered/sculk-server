package de.bauhd.sculk.connection;

import java.net.SocketAddress;

/**
 * Represents a connection to the server.
 */
public interface Connection {

    /**
     * Gets the protocol version of the client connection.
     * @return the protocol version
     */
    int getProtocolVersion();

    /**
     * Gets the address of the connection.
     * @return the address
     */
    SocketAddress getAddress();
}

package de.bauhd.sculk.connection;

import java.net.SocketAddress;

/**
 * Represents a connection to the server.
 */
public interface Connection {

  /**
   * Gets the protocol version of the client connection.
   *
   * @return the protocol version
   * @since 1.0.0
   */
  int getProtocolVersion();

  /**
   * Gets the address of the connection.
   *
   * @return the address
   * @since 1.0.0
   */
  SocketAddress getAddress();
}

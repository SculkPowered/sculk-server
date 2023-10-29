package io.github.sculkpowered.server.connection;

import java.net.SocketAddress;
import org.jetbrains.annotations.NotNull;

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
  int protocolVersion();

  /**
   * Gets the address of the connection.
   *
   * @return the address
   * @since 1.0.0
   */
  @NotNull SocketAddress address();
}

package de.bauhd.minecraft.server.connection;

import java.net.SocketAddress;

public interface Connection {

    int getProtocolVersion();

    SocketAddress getAddress();
}

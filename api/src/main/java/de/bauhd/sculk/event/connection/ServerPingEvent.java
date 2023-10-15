package de.bauhd.sculk.event.connection;

import de.bauhd.sculk.connection.Connection;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an event that fires when a client requests a status response from the server.
 */
public final class ServerPingEvent {

  private final @NotNull Connection connection;
  private Response response;

  public ServerPingEvent(@NotNull Connection connection) {
    this.connection = connection;
  }

  public @NotNull Connection getConnection() {
    return this.connection;
  }

  public void setResponse(Response response) {
    this.response = response;
  }

  public Response getResponse() {
    return this.response;
  }

  public static Response response() {
    return new Response();
  }

  public final static class Response {

    private String name;
    private int protocol = Integer.MIN_VALUE;
    private int online = Integer.MIN_VALUE;
    private int max;
    private Component description;

    public String name() {
      return this.name;
    }

    public Response name(String name) {
      this.name = name;
      return this;
    }

    public int protocol() {
      return this.protocol;
    }

    public Response protocol(int protocol) {
      this.protocol = protocol;
      return this;
    }

    public int online() {
      return this.online;
    }

    public Response online(int online) {
      this.online = online;
      return this;
    }

    public int max() {
      return this.max;
    }

    public Response max(int max) {
      this.max = max;
      return this;
    }

    public Component description() {
      return this.description;
    }

    public Response description(Component description) {
      this.description = description;
      return this;
    }
  }
}

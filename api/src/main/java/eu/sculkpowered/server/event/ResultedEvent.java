package eu.sculkpowered.server.event;

import org.jetbrains.annotations.NotNull;

public abstract class ResultedEvent<T extends ResultedEvent.Result> {

  protected T result;

  public void result(@NotNull T result) {
    this.result = result;
  }

  public T result() {
    return this.result;
  }

  public static abstract class Result {

    private final boolean allowed;

    public Result(boolean allowed) {
      this.allowed = allowed;
    }

    public boolean allowed() {
      return this.allowed;
    }

    public boolean denied() {
      return !this.allowed;
    }
  }

  public static final class GenericResult extends Result {

    private static final GenericResult ALLOWED = new GenericResult(true);
    private static final GenericResult DENIED = new GenericResult(false);

    public static GenericResult allow() {
      return ALLOWED;
    }

    public static GenericResult deny() {
      return DENIED;
    }

    private GenericResult(boolean allowed) {
      super(allowed);
    }
  }
}

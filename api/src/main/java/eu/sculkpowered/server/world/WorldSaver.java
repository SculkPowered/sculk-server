package eu.sculkpowered.server.world;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public abstract class WorldSaver {

  private WorldSaver() {
  }

  public static @NotNull Anvil anvil(@NotNull Path path) {
    return new Anvil(path);
  }

  public static @NotNull Slime slime(@NotNull OutputStream outputStream) {
    return slime(new DataOutputStream(outputStream));
  }

  public static @NotNull Slime slime(@NotNull DataOutputStream outputStream) {
    return new Slime(outputStream);
  }

  public static final class Anvil extends WorldSaver {

    private final Path path;

    public Anvil(@NotNull Path path) {
      this.path = path;
    }

    public Path path() {
      return this.path;
    }
  }

  public static final class Slime extends WorldSaver {

    private final DataOutputStream outputStream;

    public Slime(DataOutputStream outputStream) {
      this.outputStream = outputStream;
    }

    public DataOutputStream outputStream() {
      return this.outputStream;
    }
  }
}

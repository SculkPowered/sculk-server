package eu.sculkpowered.server.world;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jetbrains.annotations.NotNull;

public abstract class WorldLoader {

  private boolean blockEntities;
  private boolean entities;

  private WorldLoader() {
    this.blockEntities = true;
    this.entities = true;
  }

  public boolean blockEntities() {
    return this.blockEntities;
  }

  public @NotNull WorldLoader blockEntities(boolean blockEntities) {
    this.blockEntities = blockEntities;
    return this;
  }

  public boolean entities() {
    return this.entities;
  }

  public @NotNull WorldLoader entities(boolean entities) {
    this.entities = entities;
    return this;
  }

  public static @NotNull Anvil anvil(@NotNull Path path) {
    return new Anvil(path);
  }

  public static @NotNull Slime slime(byte @NotNull [] bytes) {
    return slime(new ByteArrayInputStream(bytes));
  }

  public static @NotNull Slime slime(@NotNull Path path) throws IOException {
    return slime(Files.newInputStream(path));
  }

  public static @NotNull Slime slime(@NotNull InputStream inputStream) {
    return slime(new DataInputStream(inputStream));
  }

  public static @NotNull Slime slime(@NotNull DataInputStream inputStream) {
    return new Slime(inputStream);
  }

  public static final class Anvil extends WorldLoader {

    private final Path path;

    public Anvil(@NotNull Path path) {
      this.path = path;
    }

    public Path path() {
      return this.path;
    }
  }

  public static final class Slime extends WorldLoader {

    private final DataInputStream inputStream;

    public Slime(DataInputStream inputStream) {
      this.inputStream = inputStream;
    }

    public DataInputStream inputStream() {
      return this.inputStream;
    }
  }
}

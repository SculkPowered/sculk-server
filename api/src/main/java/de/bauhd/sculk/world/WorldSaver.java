package de.bauhd.sculk.world;

import org.jetbrains.annotations.NotNull;

import java.io.OutputStream;
import java.nio.file.Path;

public abstract class WorldSaver {

    private WorldSaver() {}

    public static @NotNull Anvil anvil(@NotNull Path path) {
        return new Anvil(path);
    }

    public static @NotNull Slime slime(@NotNull OutputStream outputStream) {
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

        private final OutputStream outputStream;

        public Slime(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        public OutputStream outputStream() {
            return this.outputStream;
        }
    }
}

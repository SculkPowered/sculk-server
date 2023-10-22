package io.github.sculkpowered.server.code.generator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.function.Consumer;

final class DataGenerator {

  private final StringBuffer stringBuffer = new StringBuffer();
  private final byte[] bytes = new byte[2048];

  public DataGenerator(Path tmp) throws IOException {
    final var process = new ProcessBuilder(
        "java", "-DbundlerMainClass=net.minecraft.data.Main",
        "-jar", "../server.jar",
        "--reports", "--server"
    ).directory(tmp.toFile()).start();

    final Consumer<String> error = System.err::println;
    final Consumer<String> input = System.out::println;
    while (process.isAlive()) {
      this.readStream(process.getErrorStream(), error);
      this.readStream(process.getInputStream(), input);
      try {
        //noinspection BusyWait
        Thread.sleep(50L);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void readStream(final InputStream stream, final Consumer<String> consumer)
      throws IOException {
    var length = 0;
    while (stream.available() > 0
        && (length = stream.read(this.bytes, 0, this.bytes.length)) != -1) {
      this.stringBuffer.append(new String(this.bytes, 0, length, StandardCharsets.UTF_8));
    }
    final var string = this.stringBuffer.toString();
    if (string.contains("\n")) {
      for (final var text : string.split("\n")) {
        if (!text.trim().isEmpty()) {
          consumer.accept(text);
        }
      }
    }
    this.stringBuffer.setLength(0);
  }
}

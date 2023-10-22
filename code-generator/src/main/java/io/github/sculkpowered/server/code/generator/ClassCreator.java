package io.github.sculkpowered.server.code.generator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

final class ClassCreator {

  private final Path path;
  private final String pckage;
  private final String name;
  private String[] imports;
  private String addition;
  private String[] inner;
  private Type type;

  public ClassCreator(final Path path, final String pckage, final String name) {
    this.path = path;
    this.pckage = pckage;
    this.name = name;
  }

  public ClassCreator imports(final String... imports) {
    this.imports = imports;
    return this;
  }

  public ClassCreator type(final Type type) {
    this.type = type;
    return this;
  }

  public ClassCreator addition(String addition) {
    this.addition = addition;
    return this;
  }

  public ClassCreator inner(String... inner) {
    this.inner = inner;
    return this;
  }

  public void create() {
    try (final var writer = Files.newBufferedWriter(path)) {
      writer.write("package " + this.pckage + ";\n");
      if (this.imports != null) {
        writer.write("\n");
        for (final var imprt : this.imports) {
          writer.write("import " + imprt + ";\n");
        }
      }
      writer.write("\n");
      writer.write(this.type.type + " " + this.name + " " + this.addition + " {");
      if (this.inner != null) {
        writer.write("\n\n");
        for (final var s : this.inner) {
          writer.write(s + "\n");
        }
      }
      writer.write("}");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public enum Type {
    PROTECTED("final class"),
    INTERFACE("public interface");

    private final String type;

    Type(final String type) {
      this.type = type;
    }
  }
}

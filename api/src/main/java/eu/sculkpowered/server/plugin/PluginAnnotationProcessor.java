package eu.sculkpowered.server.plugin;

import java.io.IOException;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;

/**
 * Represents an annotation processor that creates the plugin file for a plugin.
 */
@SupportedAnnotationTypes("eu.sculkpowered.server.plugin.PluginDescription")
public final class PluginAnnotationProcessor extends AbstractProcessor {

  @Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    if (roundEnv.processingOver()) {
      return false;
    }

    roundEnv.getElementsAnnotatedWith(PluginDescription.class).forEach(element -> {
      if (element.getKind() == ElementKind.CLASS) {
        try {
          final var file = this.processingEnv.getFiler()
              .createResource(StandardLocation.CLASS_OUTPUT, "", "plugin");
          try (final var writer = file.openWriter()) {
            writer.write(((TypeElement) element).getQualifiedName().toString());
          }
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });

    return false;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.RELEASE_17;
  }
}

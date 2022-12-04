package de.bauhd.minecraft.server.api.module;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.util.Set;

@SupportedAnnotationTypes("de.bauhd.minecraft.server.api.module.ModuleDescription")
public final class ModuleAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            return false;
        }

        roundEnv.getElementsAnnotatedWith(ModuleDescription.class).forEach(element -> {
            if (element.getKind() == ElementKind.CLASS) {
                try {
                    final var file = this.processingEnv.getFiler()
                            .createResource(StandardLocation.CLASS_OUTPUT, "", "module");
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
}

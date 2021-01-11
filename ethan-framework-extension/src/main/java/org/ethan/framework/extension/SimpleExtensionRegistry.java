package org.ethan.framework.extension;

import lombok.Getter;
import lombok.Setter;
import org.ethan.framework.extension.annotation.Extension;
import org.ethan.framework.extension.exception.IllegalExtensionException;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class SimpleExtensionRegistry implements ExtensionRegistry {

    private ExtensionRepository repository;

    public SimpleExtensionRegistry(ExtensionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void register(Object extension, Class<?>... extensionClazz) {
        Extension extensionAnnotation = AnnotationUtils.findAnnotation(extension.getClass(), Extension.class);
        if (Objects.nonNull(extensionAnnotation)) {
            registerWithAnnotation(extension, extensionAnnotation);
            return;
        }
        if (extension instanceof ExtensionPoint) {
            registerWithExtensionPoint(extension, extensionClazz);
            return;
        }
        throw new IllegalExtensionException(extension);
    }

    private void registerWithAnnotation(Object extension, Extension extensionAnnotation) {
        Set<Scene> scenes = collectScenes(extensionAnnotation);
        Class<?>[] classes = extensionAnnotation.target();
        if (classes.length == 0) {
            classes = new Class[]{ extension.getClass() };
        }
        save(scenes, classes, extension);
    }

    private void registerWithExtensionPoint(Object extension, Class<?>... extensionClazz) {
        ExtensionPoint extensionPoint = (ExtensionPoint) extension;
        Class<?>[] classes = extensionClazz;
        if (Objects.isNull(classes) || classes.length == 0) {
            classes = new Class[]{ extension.getClass() };
        }
        save(extensionPoint.focus(), classes, extension);
    }

    private Set<Scene> collectScenes(Extension extensionAnnotation) {
        return Arrays.stream(extensionAnnotation.platform()).flatMap(platform ->
                Arrays.stream(extensionAnnotation.module()).flatMap(module ->
                        Arrays.stream(extensionAnnotation.function()).flatMap(function ->
                                Arrays.stream(extensionAnnotation.client()).map(client ->
                                        Scene.of(platform, module, function, client))))).collect(Collectors.toSet());
    }

    private void save(Collection<Scene> scenes, Class<?>[] classes, Object extension) {
        scenes.forEach(scene -> Arrays.stream(classes).forEach(clazz -> repository.save(scene, clazz, extension)));
    }

}

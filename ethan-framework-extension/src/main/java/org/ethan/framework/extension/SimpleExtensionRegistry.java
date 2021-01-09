package org.ethan.framework.extension;

import lombok.Getter;
import lombok.Setter;
import org.ethan.framework.extension.annotation.Extension;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 扩展点注册表简单实现
 * @author Ethan Zhang
 */
@Getter
@Setter
public class SimpleExtensionRegistry implements ExtensionRegistry {

    private ExtensionRepository repository;

    public SimpleExtensionRegistry(ExtensionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void register(Object extension) {
        Class<?> extensionClazz = extension.getClass();
        Extension extensionAnnotation = AnnotationUtils.findAnnotation(extensionClazz, Extension.class);
        if (Objects.nonNull(extensionAnnotation)) {
            Set<Scene> scenes = collectScenes(extensionAnnotation);
            save(scenes, extensionAnnotation.target(), extension);
            return;
        }
        if (extension instanceof ExtensionPoint) {
            ExtensionPoint extensionPoint = (ExtensionPoint) extension;
            save(extensionPoint.focus(), extensionClazz.getInterfaces(), extension);
            return;
        }
        throw new IllegalArgumentException("extension must be annotated with @Extension or implement ExtensionPoint!");
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

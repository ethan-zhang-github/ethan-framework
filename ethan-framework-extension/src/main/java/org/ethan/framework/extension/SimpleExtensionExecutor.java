package org.ethan.framework.extension;

import lombok.Getter;
import lombok.Setter;
import org.ethan.framework.extension.exception.ExtensionNotFoundException;

import java.util.Objects;

@Getter
@Setter
public class SimpleExtensionExecutor implements ExtensionExecutor {

    private ExtensionRepository repository;

    public SimpleExtensionExecutor(ExtensionRepository repository) {
        this.repository = repository;
    }

    @Override
    public <E> E locate(Scene scene, Class<E> extensionClazz) {
        E extension = repository.search(scene, extensionClazz);
        if (Objects.isNull(extension)) {
            throw new ExtensionNotFoundException(scene, extensionClazz);
        }
        return extension;
    }

}

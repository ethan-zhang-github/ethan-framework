package org.ethan.framework.extension.exception;

import lombok.Getter;
import lombok.Setter;
import org.ethan.framework.extension.Scene;

@Getter
@Setter
public class ExtensionNotFoundException extends ExtensionException {

    private Scene scene;

    private Class<?> extensionClazz;

    public ExtensionNotFoundException(Scene scene, Class<?> extensionClazz) {
        super(String.format("extension not found by scene (%s) and clazz (%s)!", scene, extensionClazz));
        this.scene = scene;
        this.extensionClazz = extensionClazz;
    }

}

package org.ethan.framework.extension.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IllegalExtensionException extends ExtensionException {

    private Class<?> extensionClazz;

    private Object extension;

    public IllegalExtensionException(Class<?> extensionClazz) {
        super(String.format("illegal extension class %s is not the subclass of ExtensionPoint!", extensionClazz.getName()));
        this.extensionClazz = extensionClazz;
    }

    public IllegalExtensionException(Object extension) {
        super(String.format("%s must be annotated with @Extension or implement ExtensionPoint!", extension.getClass().getName()));
        this.extension = extension;
        this.extensionClazz = extension.getClass();
    }

}

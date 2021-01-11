package org.ethan.framework.extension.spi;

import lombok.Getter;
import lombok.Setter;
import org.ethan.framework.extension.ExtensionRegistry;

@Getter
@Setter
public abstract class AbstractExtensionLoader implements ExtensionLoader {

    protected ExtensionRegistry extensionRegistry;

    public AbstractExtensionLoader(ExtensionRegistry extensionRegistry) {
        this.extensionRegistry = extensionRegistry;
    }

}

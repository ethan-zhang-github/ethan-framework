package org.ethan.framework.extension.spi;

import org.ethan.framework.extension.ExtensionRegistry;
import org.ethan.framework.extension.annotation.Extension;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class ApplicationContextExtensionLoader extends AbstractExtensionLoader implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public ApplicationContextExtensionLoader(ExtensionRegistry extensionRegistry) {
        super(extensionRegistry);
    }

    @Override
    public void load() {
        Map<String, Object> extensions = applicationContext.getBeansWithAnnotation(Extension.class);
        extensions.values().forEach(extensionRegistry::register);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}

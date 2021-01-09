package org.ethan.framework.extension.spring;

import org.ethan.framework.extension.ExtensionRegistry;
import org.ethan.framework.extension.annotation.Extension;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class SpringExtensionLoader implements ApplicationContextAware, SmartInitializingSingleton {

    private ApplicationContext applicationContext;

    private final ExtensionRegistry extensionRegistry;

    public SpringExtensionLoader(ExtensionRegistry extensionRegistry) {
        this.extensionRegistry = extensionRegistry;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> extensions = applicationContext.getBeansWithAnnotation(Extension.class);
        extensions.values().forEach(extensionRegistry::register);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}

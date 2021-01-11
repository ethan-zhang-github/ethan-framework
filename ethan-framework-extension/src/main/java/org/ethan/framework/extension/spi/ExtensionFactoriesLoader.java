package org.ethan.framework.extension.spi;

import lombok.extern.slf4j.Slf4j;
import org.ethan.framework.extension.ExtensionPoint;
import org.ethan.framework.extension.ExtensionRegistry;
import org.ethan.framework.extension.exception.IllegalExtensionException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class ExtensionFactoriesLoader extends AbstractExtensionLoader implements BeanClassLoaderAware {

    public static final String FACTORIES_RESOURCE_LOCATION = "ethan/extension.factories";

    private ClassLoader beanClassLoader;

    public ExtensionFactoriesLoader(ExtensionRegistry extensionRegistry) {
        super(extensionRegistry);
    }

    @Override
    public void load() {
        Map<String, List<String>> factories = loadExtensionFactories();
        registerExtensions(factories);
    }

    private Map<String, List<String>> loadExtensionFactories() {
        Map<String, List<String>> result = new HashMap<>();
        try {
            Enumeration<URL> urls = beanClassLoader.getResources(FACTORIES_RESOURCE_LOCATION);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                UrlResource resource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                for (Map.Entry<?, ?> entry : properties.entrySet()) {
                    result.put(String.valueOf(entry.getKey()).trim(),
                            Arrays.stream(String.valueOf(entry.getValue()).trim().split(",")).collect(Collectors.toList()));
                }
            }
        } catch (IOException e) {
            log.error("load extension factories error!", e);
        }
        return result;
    }

    private void registerExtensions(Map<String, List<String>> extensionFactories) {
        for (Map.Entry<String, List<String>> entry : extensionFactories.entrySet()) {
            String extensionClassName = entry.getKey();
            try {
                Class<?> extensionClazz = Class.forName(extensionClassName);
                if (!ExtensionPoint.class.isAssignableFrom(extensionClazz)) {
                    throw new IllegalExtensionException(extensionClazz);
                }
                List<String> implementClassNames = entry.getValue();
                for (String implementClassName : implementClassNames) {
                    Class<?> implementClazz = Class.forName(implementClassName);
                    Object extension = implementClazz.getConstructor().newInstance();
                    extensionRegistry.register(extension, extensionClazz);
                }
            } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException
                    | IllegalAccessException | InvocationTargetException e) {
                log.error("register extensions error!", e);
            }
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

}

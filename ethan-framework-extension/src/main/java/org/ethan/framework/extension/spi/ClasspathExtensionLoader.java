package org.ethan.framework.extension.spi;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.SmartInitializingSingleton;

public class ClasspathExtensionLoader implements BeanClassLoaderAware, SmartInitializingSingleton {

    public static final String SPI_RESOURCE_LOCATION = "ethan/extension.yaml";

    private ClassLoader beanClassLoader;

    @Override
    public void afterSingletonsInstantiated() {

    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

}

package org.ethan.framework.extension.configuration;

import org.ethan.framework.extension.*;
import org.ethan.framework.extension.spi.ApplicationContextExtensionLoader;
import org.ethan.framework.extension.spi.ExtensionFactoriesLoader;
import org.ethan.framework.extension.spi.ExtensionLoader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExtensionAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public ExtensionRepository extensionRepository() {
        return new SimpleExtensionRepository();
    }

    @ConditionalOnMissingBean
    @Bean
    public ExtensionRegistry extensionRegistry() {
        return new SimpleExtensionRegistry(extensionRepository());
    }

    @ConditionalOnMissingBean
    @Bean
    public ExtensionExecutor extensionExecutor() {
        return new SimpleExtensionExecutor(extensionRepository());
    }

    @Bean
    public ExtensionLoader springExtensionLoader() {
        return new ApplicationContextExtensionLoader(extensionRegistry());
    }

    @Bean
    public ExtensionLoader extensionFactoriesLoader() {
        return new ExtensionFactoriesLoader(extensionRegistry());
    }

}

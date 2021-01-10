package org.ethan.framework.extension.configuration;

import org.ethan.framework.extension.*;
import org.ethan.framework.extension.spi.SpringExtensionLoader;
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
    public SpringExtensionLoader springExtensionLoader() {
        return new SpringExtensionLoader(extensionRegistry());
    }

}

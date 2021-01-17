package org.ethan.framework.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableBatchProcessing
@Configuration
public class BatchAutoConfiguration {

    @Lazy
    @Bean
    public ThreadPoolTaskExecutor batchTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("BATCH-");
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setQueueCapacity(executor.getCorePoolSize() << 6);
        executor.setMaxPoolSize(executor.getCorePoolSize() << 2);
        return executor;
    }

}

package hu.cscs.nhw.jmspoc.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class AsynConfig {

    @Bean("jmsSenderExecutor")
    public Executor jmsSenderExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(8);
        executor.setCorePoolSize(8);
        executor.setQueueCapacity(1024);
        executor.setThreadNamePrefix("jms-sender-");
        log.info("[ jmsSenderExecutor  ] executor created with thread maxPoolSize: [{}]; corePoolSize: [{}]; prefix: [{}]", executor.getMaxPoolSize(),
                executor.getCorePoolSize(), executor.getThreadNamePrefix());
        executor.initialize();
        return executor;
    }
}

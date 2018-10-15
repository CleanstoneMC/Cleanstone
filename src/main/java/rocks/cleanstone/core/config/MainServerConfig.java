package rocks.cleanstone.core.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableCaching
@EnableAsync
@EnableAutoConfiguration
@EnableScheduling
public class MainServerConfig {

    @Bean
    public MessageSource messageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("lang/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        final ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        return threadPoolTaskScheduler;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        final ThreadPoolTaskExecutor taskExecutor = createTaskExecutor(10, "taskExecutor");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public TaskExecutor playerExec() {
        final ThreadPoolTaskExecutor taskExecutor = createTaskExecutor(10, "playerExec");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public TaskExecutor mcLoginExec() {
        final ThreadPoolTaskExecutor taskExecutor = createTaskExecutor(10, "mcLoginExec");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public TaskExecutor chatExec() {
        final ThreadPoolTaskExecutor taskExecutor = createTaskExecutor(10, "chatExec");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public TaskExecutor commandExec() {
        final ThreadPoolTaskExecutor taskExecutor = createTaskExecutor(10, "commandExec");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public TaskExecutor worldExec() {
        final ThreadPoolTaskExecutor executor = createTaskExecutor(5000, "worldExec");
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(128);
        executor.setQueueCapacity(50);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }

    private ThreadPoolTaskExecutor createTaskExecutor(int maxSize, String name) {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxSize);
        executor.setThreadNamePrefix(name);

        return executor;
    }
}

package rocks.cleanstone.core.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class MainServerConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("lang/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public TaskExecutor taskSheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(10);
        return threadPoolTaskScheduler;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = createTaskExecutor(10, "taskExecutor");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public TaskExecutor playerExec() {
        ThreadPoolTaskExecutor taskExecutor = createTaskExecutor(10, "playerExec");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public TaskExecutor mcLoginExec() {
        ThreadPoolTaskExecutor taskExecutor = createTaskExecutor(10, "mcLoginExec");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public TaskExecutor chatExec() {
        ThreadPoolTaskExecutor taskExecutor = createTaskExecutor(10, "chatExec");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public TaskExecutor commandExec() {
        ThreadPoolTaskExecutor taskExecutor = createTaskExecutor(10, "commandExec");
        taskExecutor.initialize();

        return taskExecutor;
    }

    @Bean
    public TaskExecutor worldExec() {
        ThreadPoolTaskExecutor executor = createTaskExecutor(5000, "worldExec");
        executor.setCorePoolSize(10);
        executor.setQueueCapacity(50);
//        rejection-policy="CALLER_RUNS" //TODO
        executor.initialize();

        return executor;
    }

    private ThreadPoolTaskExecutor createTaskExecutor(int maxSize, String name) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxSize);
        executor.setThreadNamePrefix(name);

        return executor;
    }
}

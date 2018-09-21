package rocks.cleanstone.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.core.event.CleanstoneEventPublisher;
import rocks.cleanstone.core.event.EventExecutionException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Locale;

public abstract class CleanstoneServer implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(CleanstoneServer.class);
    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    private static CleanstoneServer INSTANCE;
    private static Thread restartThread;

    protected final CleanstoneConfig cleanstoneConfig;
    protected final MinecraftConfig minecraftConfig;
    @Autowired
    protected CleanstoneEventPublisher eventPublisher;
    @Autowired
    protected MessageSource messageSource;
    @Autowired
    protected SpringBeanDefinitionProxy springBeanDefinitionProxy;
    @Autowired
    protected ConfigurableApplicationContext context;

    protected CleanstoneServer(CleanstoneConfig cleanstoneConfig, MinecraftConfig minecraftConfig) {
        this.cleanstoneConfig = cleanstoneConfig;
        this.minecraftConfig = minecraftConfig;
    }

    public static <T> T publishEvent(T event) {
        return publishEvent(event, false);
    }

    public static <T> T publishEvent(T event, boolean rethrowExceptions) throws EventExecutionException {
        return getInstance().eventPublisher.publishEvent(event, rethrowExceptions);
    }

    public static String getMessage(String id, Object... args) {
        return getMessageOfLocale(id, DEFAULT_LOCALE, args);
    }

    public static String getMessageOfLocale(String id, Locale locale, Object... args) {
        MessageSource source = getInstance().getMessageSource();
        String defaultMessage = locale == DEFAULT_LOCALE ? null : source.getMessage(id, args, DEFAULT_LOCALE);
        String message = source.getMessage(id, args, defaultMessage, locale);
        return message != null ? message : "Error: Cannot find message with id " + id;
    }

    public static CleanstoneServer getInstance() {
        return INSTANCE;
    }

    public static void stop() {
        getInstance().context.stop();
    }

    public static synchronized void restart() {
        if (restartThread != null) {
            logger.warn("Server is already restarting");
            return;
        }

        restartThread = new Thread(() -> {
            stop();
            getInstance().context.start();
            restartThread = null;
        });
        restartThread.setDaemon(false);
        restartThread.start();
    }

    public static Locale getDefaultLocale() {
        return DEFAULT_LOCALE;
    }

    @PostConstruct
    public void init() {
        INSTANCE = this;
    }

    @PreDestroy
    public void destroy() {
        INSTANCE = null;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public CleanstoneConfig getCleanstoneConfig() {
        return cleanstoneConfig;
    }

    public MinecraftConfig getMinecraftConfig() {
        return minecraftConfig;
    }

    public SpringBeanDefinitionProxy getSpringBeanDefinitionProxy() {
        return springBeanDefinitionProxy;
    }

    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    @EventListener
    public void onShutdown(ContextClosedEvent e) {
        logger.info("Shutting down");
    }
}

package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;
import rocks.cleanstone.core.event.CleanstoneEventPublisher;
import rocks.cleanstone.core.event.EventExecutionException;

import java.util.Locale;

public abstract class CleanstoneServer implements ApplicationRunner {

    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    private static CleanstoneServer INSTANCE;

    protected final CleanstoneConfig cleanstoneConfig;
    protected final MinecraftConfig minecraftConfig;
    @Autowired
    protected CleanstoneEventPublisher eventPublisher;
    @Autowired
    protected MessageSource messageSource;
    @Autowired
    protected SpringBeanDefinitionProxy springBeanDefinitionProxy;

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

    public void init() {
        INSTANCE = this;
    }

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
}

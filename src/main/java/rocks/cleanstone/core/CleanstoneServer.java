package rocks.cleanstone.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;

import java.util.Locale;

import rocks.cleanstone.core.config.CleanstoneConfig;
import rocks.cleanstone.core.config.MinecraftConfig;

public abstract class CleanstoneServer implements ApplicationRunner {

    public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    private static CleanstoneServer INSTANCE;

    protected final CleanstoneConfig cleanstoneConfig;
    protected final MinecraftConfig minecraftConfig;
    @Autowired
    protected ApplicationEventPublisher publisher;
    @Autowired
    protected MessageSource messageSource;

    protected CleanstoneServer(CleanstoneConfig cleanstoneConfig, MinecraftConfig minecraftConfig) {
        this.cleanstoneConfig = cleanstoneConfig;
        this.minecraftConfig = minecraftConfig;
    }

    public static <T> T publishEvent(T event) {
        getInstance().getPublisher().publishEvent(event);
        return event;
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

    public ApplicationEventPublisher getPublisher() {
        return publisher;
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
}

package rocks.cleanstone.game.chat.message;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Locale;

import javax.annotation.Nullable;

public enum TextColor {

    BLACK('0'), DARK_BLUE('1'), DARK_GREEN('2'), DARK_AQUA('3'), DARK_RED('4'), DARK_PURPLE('5'), GOLD('6'),
    GRAY('7'), DARK_GRAY('8'), BLUE('9'), GREEN('a'), AQUA('b'), RED('c'), LIGHT_PURPLE('d'), YELLOW('e'),
    WHITE('f');

    public static final char CONTROL_CHAR = '§';

    private final char code;

    TextColor(char code) {
        this.code = code;
    }

    @Nullable
    public static TextColor fromCode(char code) {
        return Arrays.stream(values()).filter(color -> color.getCode() == code).findFirst().orElse(null);
    }

    public String getID() {
        return name().toLowerCase(Locale.ENGLISH);
    }

    public String getName() {
        return StringUtils.capitalize(name().toLowerCase(Locale.ENGLISH).replace("_", " "));
    }

    public char getCode() {
        return code;
    }

    @Override
    public String toString() {
        return CONTROL_CHAR + "" + getCode();
    }
}

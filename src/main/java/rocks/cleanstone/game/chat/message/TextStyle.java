package rocks.cleanstone.game.chat.message;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Locale;

import javax.annotation.Nullable;

public enum TextStyle {

    OBFUSCATED('k'), BOLD('l'), STRIKETHROUGH('m'), UNDERLINE('n'), ITALIC('o'), RESET('r');

    private final char code;

    TextStyle(char code) {
        this.code = code;
    }

    @Nullable
    public static TextStyle fromCode(char code) {
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
        return TextColor.CONTROL_CHAR + "" + getCode();
    }
}

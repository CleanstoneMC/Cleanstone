package rocks.cleanstone.game.chat.message;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import rocks.cleanstone.core.CleanstoneServer;

public class Text {
    private String plainText;
    private boolean bold, italic, underlined, obfuscated, strikethrough, reset;
    private TextColor color;
    private List<Text> siblings = new ArrayList<>();

    protected Text(String plainText) {
        this.plainText = plainText;
    }

    public static Text of(String plainText) {
        return new Text(plainText);
    }

    public static Text ofLocalized(String messageID, Locale locale, Object... args) {
        return new Text(CleanstoneServer.getMessageOfLocale(messageID, locale, args));
    }

    public String getPlainText() {
        return plainText;
    }

    public Text setPlainText(String plainText) {
        this.plainText = plainText;
        return this;
    }

    public boolean isBold() {
        return bold;
    }

    public Text setBold(boolean bold) {
        this.bold = bold;
        return this;
    }

    public boolean isItalic() {
        return italic;
    }

    public Text setItalic(boolean italic) {
        this.italic = italic;
        return this;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public Text setUnderlined(boolean underlined) {
        this.underlined = underlined;
        return this;
    }

    public boolean isObfuscated() {
        return obfuscated;
    }

    public Text setObfuscated(boolean obfuscated) {
        this.obfuscated = obfuscated;
        return this;
    }

    public boolean isStrikethrough() {
        return strikethrough;
    }

    public Text setStrikethrough(boolean strikethrough) {
        this.strikethrough = strikethrough;
        return this;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public TextColor getColor() {
        return color;
    }

    public Text setColor(TextColor color) {
        this.color = color;
        return this;
    }

    public Text setStyle(TextStyle style) {
        switch (style) {
            case OBFUSCATED:
                setObfuscated(true);
                break;
            case BOLD:
                setBold(true);
                break;
            case STRIKETHROUGH:
                setStrikethrough(true);
                break;
            case UNDERLINE:
                setUnderlined(true);
                break;
            case ITALIC:
                setItalic(true);
                break;
            case RESET:
                setReset(true);
        }
        return this;
    }

    public List<Text> getSiblings() {
        return siblings;
    }

    public Text setSiblings(List<Text> siblings) {
        this.siblings = siblings;
        return this;
    }

    public Text addExtra(Text extra) {
        this.siblings.add(extra);
        return this;
    }

    @Override
    public String toString() {

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("text", getPlainText());
        jsonObject.addProperty("bold", isBold());
        jsonObject.addProperty("italic", isItalic());
        jsonObject.addProperty("underlined", isUnderlined());
        jsonObject.addProperty("obfuscated", isObfuscated());
        jsonObject.addProperty("strikethrough", isStrikethrough());

        if (getColor() != null) {
            jsonObject.addProperty("color", reset ? "reset" : getColor().getID());
        }

        String extraString = getExtraString();

        if (extraString != null) {
            jsonObject.addProperty("extra", extraString);
        }

        return jsonObject.toString();
    }

    private String getExtraString() {

        if (siblings.size() == 0) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        for (Iterator<Text> iterator = siblings.iterator(); iterator.hasNext(); ) {
            Text sibling = iterator.next();

            stringBuilder.append(sibling.toString());
            if (iterator.hasNext()) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
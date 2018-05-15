package rocks.cleanstone.game.chat.message;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chat {
    private String text;
    private boolean bold;
    private boolean italic;
    private boolean underlined;
    private boolean obfuscated;
    private String color;
    private List<Chat> extra = new ArrayList<>();

    public Chat() {
    }

    public Chat(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Chat setText(String text) {
        this.text = text;
        return this;
    }

    public boolean isBold() {
        return bold;
    }

    public Chat setBold(boolean bold) {
        this.bold = bold;
        return this;
    }

    public boolean isItalic() {
        return italic;
    }

    public Chat setItalic(boolean italic) {
        this.italic = italic;
        return this;
    }

    public boolean isUnderlined() {
        return underlined;
    }

    public Chat setUnderlined(boolean underlined) {
        this.underlined = underlined;
        return this;
    }

    public boolean isObfuscated() {
        return obfuscated;
    }

    public Chat setObfuscated(boolean obfuscated) {
        this.obfuscated = obfuscated;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Chat setColor(String color) {
        this.color = color;
        return this;
    }

    public List<Chat> getExtra() {
        return extra;
    }

    public Chat setExtra(List<Chat> extra) {
        this.extra = extra;
        return this;
    }

    public Chat addExtra(Chat extra) {
        this.extra.add(extra);
        return this;
    }


    @Override
    public String toString() {

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("text", getText());
        jsonObject.addProperty("bold", isBold());
        jsonObject.addProperty("italic", isItalic());
        jsonObject.addProperty("underlined", isUnderlined());
        jsonObject.addProperty("obfuscated", isObfuscated());


        if (getColor() != null && !getColor().equals("reset")) {
            jsonObject.addProperty("color", isObfuscated());
        }

        String extraString = getExtraString();

        if (extraString != null) {
            jsonObject.addProperty("extra", extraString);
        }

        return jsonObject.toString();
    }

    private String getExtraString() {

        if (extra.size() == 0) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder("[");
        for (Iterator<Chat> iterator = extra.iterator(); iterator.hasNext(); ) {
            Chat chat = iterator.next();

            stringBuilder.append(chat.toString());
            if (iterator.hasNext()) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
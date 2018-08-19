package rocks.cleanstone.game.command.completion;

import javax.annotation.Nullable;
import rocks.cleanstone.game.chat.message.ChatMessage;

public class CompletionMatch {
    private final String match;
    private final ChatMessage tooltip;

    public CompletionMatch(String match) {
        this(match, null);
    }

    public CompletionMatch(String match, ChatMessage tooltip) {
        this.match = match;
        this.tooltip = tooltip;
    }

    public String getMatch() {
        return match;
    }

    @Nullable
    public ChatMessage getTooltip() {
        return tooltip;
    }
}

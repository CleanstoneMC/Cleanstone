package rocks.cleanstone.game.command.completion.node;

public class CommandNodeFlags {
    private final NodeType nodeType;
    private boolean isExecutable;
    private boolean hasRedirect;
    private boolean hasSuggestionType;

    public CommandNodeFlags(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public boolean isExecutable() {
        return isExecutable;
    }

    public void setExecutable(boolean executable) {
        isExecutable = executable;
    }

    public boolean isHasRedirect() {
        return hasRedirect;
    }

    public void setHasRedirect(boolean hasRedirect) {
        this.hasRedirect = hasRedirect;
    }

    public boolean isHasSuggestionType() {
        return hasSuggestionType;
    }

    public void setHasSuggestionType(boolean hasSuggestionType) {
        this.hasSuggestionType = hasSuggestionType;
    }

    public int getBitMask() {
        return 0; //TODO: Add Bitmask https://wiki.vg/Command_Data#Flags
    }
}

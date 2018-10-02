package rocks.cleanstone.game.command.completion.node;

import java.util.ArrayList;
import java.util.List;

public class CommandNode {

    private final CommandNodeFlags commandNodeFlags;
    private final List<CommandNode> children;
    private Integer redirectNodeIndex;
    private String name;
    private Parser parser;
//TODO: Properties
    private SuggestionsType suggestionsType;

    private CommandNode(NodeType nodeType) {
        children = new ArrayList<>();
        commandNodeFlags = new CommandNodeFlags(nodeType);
    }

    public CommandNode(String name, NodeType nodeType) {
        this(nodeType);
        this.name = name;
    }

    public CommandNodeFlags getCommandNodeFlags() {
        return commandNodeFlags;
    }

    public List<CommandNode> getChildren() {
        return children;
    }

    public Integer getRedirectNodeIndex() {
        return redirectNodeIndex;
    }

    public String getName() {
        return name;
    }

    public Parser getParser() {
        return parser;
    }

    public SuggestionsType getSuggestionsType() {
        return suggestionsType;
    }
}


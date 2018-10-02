package rocks.cleanstone.game.command.completion.node;

public enum NodeType {
    ROOT(0),
    LITERAL(1),
    ARGUMENT(2);

    private final int id;

    NodeType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

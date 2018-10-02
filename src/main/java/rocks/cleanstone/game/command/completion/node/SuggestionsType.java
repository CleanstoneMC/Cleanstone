package rocks.cleanstone.game.command.completion.node;

public enum SuggestionsType {
    MINECRAFT_ASK_SERVER("minecraft:ask_server"),
    MINECRAFT_ALL_RECIPES("minecraft:all_recipes"),
    MINECRAFT_AVAILABLE_SOUNDS("minecraft:available_sounds"),
    MINECRAFT_SUMMONABLE_ENTITIES("minecraft:summonable_entities");

    private final String identifier;

    SuggestionsType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}

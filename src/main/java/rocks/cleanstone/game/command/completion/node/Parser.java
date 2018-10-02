package rocks.cleanstone.game.command.completion.node;

public enum Parser {
    BRIGADIER_BOOL("brigadier:bool"),
    BRIGADIER_DOUBLE("brigadier:double"),
    BRIGADIER_FLOAT("brigadier:float"),
    BRIGADIER_INTEGER("brigadier:integer"),
    BRIGADIER_STRING("brigadier:string"),
    MINECRAFT_ENTITY("minecraft:entity"),
    MINECRAFT_GAME_PROFILE("minecraft:game_profile"),
    MINECRAFT_BLOCK_POS("minecraft:block_pos"),
    MINECRAFT_VEC3("minecraft:vec3"),
    MINECRAFT_VEC2("minecraft:vec2"),
    MINECRAFT_BLOCK_STATE("minecraft:block_state"),
    MINECRAFT_BLOCK_PREDICATE("minecraft:block_predicate"),
    MINECRAFT_ITEM_STACK("minecraft:item_stack"),
    MINECRAFT_ITEM_PREDICATE("minecraft:item_predicate"),
    MINECRAFT_COLOR("minecraft:color"),
    MINECRAFT_COMPONENT("minecraft:component"),
    MINECRAFT_MESSAGE("minecraft:message"),
    MINECRAFT_NBT("minecraft:nbt"),
    MINECRAFT_NBT_PATH("minecraft:nbt_path"),
    MINECRAFT_OBJECTIVE("minecraft:objective"),
    MINECRAFT_OBJECTIVE_CRITERIA("minecraft:objective_criteria"),
    MINECRAFT_OPERATION("minecraft:operation"),
    MINECRAFT_PARTICLE("minecraft:particle"),
    MINECRAFT_ROTATION("minecraft:rotation"),
    MINECRAFT_SCOREBOARD_SLOT("minecraft:scoreboard_slot"),
    MINECRAFT_SCORE_HOLDER("minecraft:score_holder"),
    MINECRAFT_SWIZZLE("minecraft:swizzle"),
    MINECRAFT_TEAM("minecraft:team"),
    MINECRAFT_ITEM_SLOT("minecraft:item_slot"),
    MINECRAFT_RESOURCE_LOCATION("minecraft:resource_location"),
    MINECRAFT_MOB_EFFECT("minecraft:mob_effect"),
    MINECRAFT_FUNCTION("minecraft:function"),
    MINECRAFT_ENTITY_ANCHOR("minecraft:entity_anchor"),
    MINECRAFT_RANGE("minecraft:range"),
    MINECRAFT_ITEM_ENCHANTMENT("minecraft:item_enchantment");

    private final String identifier;

    Parser(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}

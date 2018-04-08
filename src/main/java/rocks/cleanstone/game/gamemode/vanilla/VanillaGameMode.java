package rocks.cleanstone.game.gamemode.vanilla;

import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.GameModeRuleSet;

public enum VanillaGameMode implements GameMode {
    SURVIVAL(0, Survival.class),
    CREATIVE(1, Creative.class),
    ADVENTURE(2, Adventure.class),
    SPECTATOR(3, Spectator.class);

    private final int typeId;

    VanillaGameMode(int typeId, Class<? extends GameModeRuleSet> gameModeRuleSetClass) {
        this.typeId = typeId;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }
}

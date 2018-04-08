package rocks.cleanstone.game.gamemode.vanilla;

import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.GameModeRuleSet;

public enum VanillaGameMode implements GameMode {
    ADVENTURE(2, Adventure.class), CREATIVE(1, Creative.class), SPECTATOR(3, Spectator.class), SURVIVAL(0, Survival.class);

    private final int typeId;

    VanillaGameMode(int typeId, Class<? extends GameModeRuleSet> gameModeRuleSetClass) {
        this.typeId = typeId;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }
}

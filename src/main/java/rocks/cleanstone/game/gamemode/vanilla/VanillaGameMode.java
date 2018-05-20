package rocks.cleanstone.game.gamemode.vanilla;

import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.GameModeRuleSet;

public enum VanillaGameMode implements GameMode {
    SURVIVAL(0, new Survival()),
    CREATIVE(1, new Creative()),
    ADVENTURE(2, new Adventure()),
    SPECTATOR(3, new Spectator());

    private final int typeId;
    private final GameModeRuleSet gameModeRuleSet;

    VanillaGameMode(int typeId, GameModeRuleSet gameModeRuleSet) {
        this.typeId = typeId;
        this.gameModeRuleSet = gameModeRuleSet;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    public GameModeRuleSet getGameModeRuleSet() {
        return gameModeRuleSet;
    }
}

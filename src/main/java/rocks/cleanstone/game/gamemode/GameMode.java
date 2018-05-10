package rocks.cleanstone.game.gamemode;

public interface GameMode {
    int getTypeId();
    Class<? extends GameModeRuleSet> getGameModeRuleSetClass();
}

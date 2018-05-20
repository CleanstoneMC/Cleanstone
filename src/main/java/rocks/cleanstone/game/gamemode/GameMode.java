package rocks.cleanstone.game.gamemode;

public interface GameMode {
    int getTypeId();

    GameModeRuleSet getGameModeRuleSet();
}

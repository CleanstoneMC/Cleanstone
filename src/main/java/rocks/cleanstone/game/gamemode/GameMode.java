package rocks.cleanstone.game.gamemode;

import java.util.Collection;

import rocks.cleanstone.net.packet.enums.PlayerAbilities;

public interface GameMode {
    int getTypeId();

    String getName();

    GameModeRuleSet getRuleSet();

    Collection<PlayerAbilities> getPlayerAbilities();
}

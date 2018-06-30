package rocks.cleanstone.game.gamemode;

import java.io.Serializable;
import java.util.Collection;

import rocks.cleanstone.net.packet.enums.PlayerAbility;

public interface GameMode extends Serializable {
    int getTypeId();

    String getName();

    GameModeRuleSet getRuleSet();

    Collection<PlayerAbility> getPlayerAbilities();
}

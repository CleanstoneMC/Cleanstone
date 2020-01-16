package rocks.cleanstone.game.gamemode;

import rocks.cleanstone.endpoint.minecraft.java.net.packet.enums.PlayerAbility;

import java.io.Serializable;
import java.util.Collection;

public interface GameMode extends Serializable {
    int getTypeId();

    String getName();

    GameModeRuleSet getRuleSet();

    Collection<PlayerAbility> getPlayerAbilities();
}

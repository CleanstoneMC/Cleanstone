package rocks.cleanstone.game.gamemode;

import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbilities;

import java.util.Collection;

public interface GameMode {
    int getTypeId();

    GameModeRuleSet getRuleSet();

    Collection<PlayerAbilities> getPlayerAbilities();
}

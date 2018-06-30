package rocks.cleanstone.game.gamemode.vanilla;

import com.google.common.base.CaseFormat;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.annotation.Nullable;

import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.GameModeRuleSet;
import rocks.cleanstone.net.packet.enums.PlayerAbility;

public enum VanillaGameMode implements GameMode {
    SURVIVAL(0, new Survival()),
    CREATIVE(1, new Creative()),
    ADVENTURE(2, new Adventure()),
    SPECTATOR(3, new Spectator());

    private final int typeId;
    private final GameModeRuleSet ruleSet;

    VanillaGameMode(int typeId, GameModeRuleSet ruleSet) {
        this.typeId = typeId;
        this.ruleSet = ruleSet;
    }

    @Nullable
    public static VanillaGameMode byID(int typeID) {
        return Arrays.stream(values()).filter(gameMode -> gameMode.getTypeId() == typeID)
                .findAny().orElse(null);
    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    @Override
    public String getName() {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, name());
    }

    public GameModeRuleSet getRuleSet() {
        return ruleSet;
    }

    @Override
    public Collection<PlayerAbility> getPlayerAbilities() {
        Collection<PlayerAbility> abilities = new HashSet<>();
        if (ruleSet.canFly()) abilities.add(PlayerAbility.CAN_FLY);
        if (ruleSet.hasCreativeInventory()) abilities.add(PlayerAbility.IS_CREATIVE);
        if (!ruleSet.canGetDamage()) abilities.add(PlayerAbility.IS_INVULNERABLE);
        return abilities;
    }
}

package rocks.cleanstone.game.gamemode.vanilla;

import rocks.cleanstone.game.gamemode.GameModeRuleSet;

public class Creative implements GameModeRuleSet {

    @Override
    public boolean canFly() {
        return true;
    }

    @Override
    public boolean canGetDamage() {
        return false;
    }

    @Override
    public boolean hasUnlimitedUsage() {
        return true;
    }

    @Override
    public boolean hasCreativeInventory() {
        return true;
    }

    @Override
    public boolean canInteract() {
        return true;
    }

    @Override
    public boolean canChat() {
        return true;
    }

    @Override
    public boolean canModifyWorld() {
        return true;
    }
}

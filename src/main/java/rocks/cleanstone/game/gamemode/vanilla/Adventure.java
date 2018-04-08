package rocks.cleanstone.game.gamemode.vanilla;

import rocks.cleanstone.game.gamemode.GameModeRuleSet;

public class Adventure implements GameModeRuleSet {

    @Override
    public boolean canFly() {
        return false;
    }

    @Override
    public boolean canGetDamage() {
        return true;
    }

    @Override
    public boolean hasUnlimitedUsage() {
        return false;
    }

    @Override
    public boolean hasCreativeInventory() {
        return false;
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
        return false;
    }
}

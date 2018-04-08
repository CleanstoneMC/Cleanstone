package rocks.cleanstone.game.gamemode.vanilla;

import rocks.cleanstone.game.gamemode.GameModeRuleSet;

public class Spectator implements GameModeRuleSet {

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
        return false;
    }

    @Override
    public boolean hasCreativeInventory() {
        return false;
    }

    @Override
    public boolean canInteract() {
        return false;
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

package rocks.cleanstone.game.gamemode;

public interface GameModeRuleSet {
    boolean canFly();

    boolean canGetDamage();

    boolean hasUnlimitedUsage();

    boolean hasCreativeInventory();

    boolean canInteract();

    boolean canChat();

    boolean canModifyWorld();
}

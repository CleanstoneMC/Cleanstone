package rocks.cleanstone.game.material.block;

public enum VanillaMiningLevel implements MiningLevel {
    HAND(0),
    WOODEN(10),
    GOLDEN(20),
    STONE(30),
    IRON(40),
    DIAMOND(50),
    INFINITE(-1);

    private final int mineLevel;

    VanillaMiningLevel(int mineLevel) {
        this.mineLevel = mineLevel;
    }

    public int getMineLevel() {
        return mineLevel;
    }
}

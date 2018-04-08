package rocks.cleanstone.game.gamemode;

public enum VanillaGameMode implements GameMode {
    ADVENTURE(2, Adventure.class), CREATIVE(1, Creative.class), SPECTATOR(3, Spectator.class), SURVIVAL(0, Survival.class);

    private final int typeId;

    VanillaGameMode(int typeId, Class<? extends GameMode> gameModeClass) {
        this.typeId = typeId;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }
}

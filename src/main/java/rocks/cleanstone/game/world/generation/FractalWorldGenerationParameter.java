package rocks.cleanstone.game.world.generation;

public enum FractalWorldGenerationParameter {
    AMPLITUDE,
    FREQUENCY;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}

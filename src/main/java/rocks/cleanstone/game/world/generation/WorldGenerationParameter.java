package rocks.cleanstone.game.world.generation;

public enum WorldGenerationParameter {
    FREQUENCY,
    FRACTAL_OCTAVES,
    FRACTAL_GAIN,
    FRACTAL_LACUNARITY,
    AMPLITUDE,
    LOWEST_Y,
    POWER;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}

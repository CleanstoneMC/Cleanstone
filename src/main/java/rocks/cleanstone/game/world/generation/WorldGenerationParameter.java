package rocks.cleanstone.game.world.generation;

public enum WorldGenerationParameter {
    FREQUENCY,
    FRACTAL_OCTAVES,
    FRACTAL_GAIN,
    FRACTAL_LACUNARITY;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}

package rocks.cleanstone.game.material;

public abstract class BlockMaterial implements Material {
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public String getMinecraftID() {
        return null;
    }
}

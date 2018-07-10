package rocks.cleanstone.game.material;

/**
 * A material in the game, this may either be an ItemType or a BlockType or both
 */
public interface Material {
    int getID();

    String getMinecraftID();
}

package rocks.cleanstone.game.material;

/**
 * A material in the game, this may either be an ItemType or a BlockType
 */
public interface Material {
    int getID();

    String getMinecraftID();
}

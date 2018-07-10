package rocks.cleanstone.game.material.block;

import rocks.cleanstone.game.material.Material;

/**
 * A block type in the game that defines the behavior of blocks with this type
 */
public interface BlockType extends Material {

    boolean hasBlockEntity();

    VanillaMiningLevel getMiningLevel();
}

package rocks.cleanstone.game.material.block;

import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.block.state.property.PropertyHolder;
import rocks.cleanstone.game.material.Material;

/**
 * A block type in the game that defines the behavior of blocks with this type
 */
public interface BlockType extends Material, PropertyHolder {

    boolean hasBlockEntity();

    VanillaMiningLevel getMiningLevel();

    Property[] getProperties();
}

package rocks.cleanstone.game.material.block;

import rocks.cleanstone.game.material.Material;

public interface BlockType {

    Material getMaterial();

    boolean hasBlockEntity();
}

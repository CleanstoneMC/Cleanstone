package rocks.cleanstone.net.protocol;

import org.springframework.beans.factory.annotation.Autowired;
import rocks.cleanstone.game.block.BlockState;
import rocks.cleanstone.game.material.MaterialRegistry;

public class SimpleProtocolBlockStateMapping implements ProtocolBlockStateMapping {

    @Autowired
    public SimpleProtocolBlockStateMapping(MaterialRegistry materialRegistry) {

    }

    @Override
    public int map(BlockState state) {
    }
}

package rocks.cleanstone.endpoint.minecraft.java.net.listener.inbound.place;

import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.minecraft.block.VanillaBlockProperties;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.game.block.Face;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.block.state.property.vanilla.StairHalf;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.player.Player;

@Component
public class BlockPlaceStairHalfProvider implements BlockPlacePropertyProvider<StairHalf> {
    @Override
    public Property<StairHalf> getSupported() {
        return VanillaBlockProperties.STAIR_HALF;
    }

    @Override
    public StairHalf computeProperty(BlockType blockType, Player player, PlayerBlockPlacementPacket packet) {
        return packet.getFace() == Face.BOTTOM ? StairHalf.TOP : StairHalf.BOTTOM;
    }
}

package rocks.cleanstone.net.minecraft.listener.inbound.place;

import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.player.Player;

public interface BlockPlacePropertyProvider<T> {
    Property<T> getSupported();

    T computeProperty(BlockType blockType, Player player, PlayerBlockPlacementPacket packet);
}

package rocks.cleanstone.endpoint.minecraft.java.net.listener.inbound.place;

import rocks.cleanstone.endpoint.minecraft.java.net.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.player.Player;

public interface BlockPlacePropertyProvider<T> {
    Property<T> getSupported();

    T computeProperty(BlockType blockType, Player player, PlayerBlockPlacementPacket packet);
}

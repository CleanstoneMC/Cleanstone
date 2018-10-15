package rocks.cleanstone.net.minecraft.listener.inbound.place;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.block.Block;
import rocks.cleanstone.game.block.ImmutableBlock;
import rocks.cleanstone.game.block.event.BlockPlaceEvent;
import rocks.cleanstone.game.block.state.property.PropertiesBuilder;
import rocks.cleanstone.game.block.state.property.Property;
import rocks.cleanstone.game.inventory.item.ItemStack;
import rocks.cleanstone.game.material.MaterialRegistry;
import rocks.cleanstone.game.material.block.BlockType;
import rocks.cleanstone.game.material.block.vanilla.VanillaBlockType;
import rocks.cleanstone.net.event.PlayerInboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.PlayerBlockPlacementPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.BlockChangePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Slf4j
@Component
public class PlayerBlockPlacementPacketListener {
    private final PlayerManager playerManager;
    private final MaterialRegistry materialRegistry;
    private final Map<Property<?>, List<BlockPlacePropertyProvider<?>>> propProviders;

    @Autowired
    public PlayerBlockPlacementPacketListener(
            PlayerManager playerManager,
            MaterialRegistry materialRegistry,
            List<BlockPlacePropertyProvider> propProviders) {
        this.playerManager = playerManager;
        this.materialRegistry = materialRegistry;

        this.propProviders = new HashMap<>();
        propProviders.forEach(prov ->
                this.propProviders.computeIfAbsent(
                        prov.getSupported(),
                        prop -> new LinkedList<>()
                ).add(prov)
        );
    }

    @Async(value = "playerExec")
    @EventListener
    public void onPacket(PlayerInboundPacketEvent<PlayerBlockPlacementPacket> playerInboundPacketEvent) {
        final PlayerBlockPlacementPacket packet = playerInboundPacketEvent.getPacket();

        final Position newBlockPosition = new Position(packet.getPosition().toVector()
                .addVector(packet.getFace().toUnitVector()));

        final Player player = playerInboundPacketEvent.getPlayer();
        final ItemStack itemStack = player.getEntity().getItemByHand(packet.getHand());

        if (itemStack == null) {
            return;
        }

        BlockType blockType = materialRegistry.getBlockTypeByItemType(itemStack.getType());
        if (blockType != null) {
            final PropertiesBuilder properties = new PropertiesBuilder(blockType);

            if (blockType == VanillaBlockType.REDSTONE_TORCH && !(packet.getFace().getOffset().endsWith("Y"))) {
                blockType = VanillaBlockType.REDSTONE_WALL_TORCH;
            } else if (blockType == VanillaBlockType.TORCH && !(packet.getFace().getOffset().endsWith("Y"))) {
                blockType = VanillaBlockType.WALL_TORCH;
            }

            final BlockType finalBlockType = blockType;
            Arrays.stream(blockType.getProperties())
                    .flatMap(prop -> propProviders.getOrDefault(prop.getProperty(), Collections.emptyList()).stream())
                    .forEach(provider -> applyProperty(finalBlockType, player, packet, properties, provider));

            final Block placedBlock = ImmutableBlock.of(blockType, properties.create());
            log.debug("{} places {} at {}", player.getName(), placedBlock, newBlockPosition);

            player.getEntity().getWorld().setBlockAt(newBlockPosition, placedBlock);
            CleanstoneServer.publishEvent(new BlockPlaceEvent(placedBlock, newBlockPosition, player, packet.getFace()));

            //TODO: Move this to a BlockPlaceEvent Listener?
            final BlockChangePacket blockChangePacket = new BlockChangePacket(newBlockPosition, placedBlock.getState());
            playerManager.broadcastPacket(blockChangePacket);
        }
    }

//    private boolean providerSupportedFor(BlockType finalBlockType, BlockPlacePropertyProvider<?> provider) {
//        return Arrays.stream(finalBlockType.getProperties()).anyMatch(def -> def.getProperty() == provider.getSupported());
//    }

    private <T> void applyProperty(BlockType finalBlockType, Player player, PlayerBlockPlacementPacket packet, PropertiesBuilder properties, BlockPlacePropertyProvider<T> provider) {
        final T value = provider.computeProperty(finalBlockType, player, packet);
        properties.withProperty(provider.getSupported(), value);
    }

}

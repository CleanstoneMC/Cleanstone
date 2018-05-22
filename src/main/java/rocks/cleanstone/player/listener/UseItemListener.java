package rocks.cleanstone.player.listener;

import org.springframework.context.event.EventListener;
import rocks.cleanstone.game.material.VanillaMaterial;
import rocks.cleanstone.net.event.InboundPacketEvent;
import rocks.cleanstone.net.minecraft.packet.inbound.UseItemPacket;
import rocks.cleanstone.net.minecraft.packet.outbound.BlockChangePacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

public class UseItemListener {

    private final PlayerManager playerManager;

    public UseItemListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventListener
    public void onBlockPlace(InboundPacketEvent inboundPacketEvent) {
        if (!(inboundPacketEvent.getPacket() instanceof UseItemPacket)) {
            return;
        }

        UseItemPacket useItemPacket = (UseItemPacket) inboundPacketEvent.getPacket();

        Player player = playerManager.getOnlinePlayer(inboundPacketEvent.getConnection());

        BlockChangePacket blockChangePacket = new BlockChangePacket(player.getEntity().getPosition(), VanillaMaterial.DIRT.getID());

        playerManager.broadcastPacket(blockChangePacket, player);
    }
}

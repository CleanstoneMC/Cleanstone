package rocks.cleanstone.game.command.cleanstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.net.minecraft.packet.data.EntityMetadata;
import rocks.cleanstone.net.minecraft.packet.enums.MobType;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnMobPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

import java.util.Collections;
import java.util.Random;
import java.util.UUID;

@Component
public class SummonCommand extends SimpleCommand {
    private final PlayerManager playerManager;

    @Autowired
    public SummonCommand(PlayerManager playerManager) {
        super("summon", Collections.emptyList(), MobType.class);
        this.playerManager = playerManager;
    }

    @Override
    public void execute(CommandMessage message) {
        final Player player = message.requireTargetPlayer();

        final MobType mobType = message.requireParameter(MobType.class);
        final double x = message.optionalParameter(Double.class)
                .orElseGet(() -> player.getEntity().getPosition().getX());
        final double y = message.optionalParameter(Double.class)
                .orElseGet(() -> player.getEntity().getPosition().getY());
        final double z = message.optionalParameter(Double.class)
                .orElseGet(() -> player.getEntity().getPosition().getZ());
        final float yaw = message.optionalParameter(Float.class)
                .orElse(0.0f);
        final float pitch = message.optionalParameter(Float.class)
                .orElse(0.0f);
        final float headPitch = message.optionalParameter(Float.class)
                .orElse(0.0f);
        final short velocityX = (short) (int) message.optionalParameter(Integer.class)
                .orElse(0);
        final short velocityY = (short) (int) message.optionalParameter(Integer.class)
                .orElse(0);
        final short velocityZ = (short) (int) message.optionalParameter(Integer.class)
                .orElse(0);

        playerManager.broadcastPacket(
                new SpawnMobPacket(
                        new Random().nextInt(Integer.MAX_VALUE),
                        UUID.randomUUID(),
                        mobType,
                        x, y, z,
                        yaw, pitch, headPitch,
                        velocityX, velocityY, velocityZ,
                        new EntityMetadata()
                )
        );
    }
}

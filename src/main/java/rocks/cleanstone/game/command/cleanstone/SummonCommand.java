package rocks.cleanstone.game.command.cleanstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Random;
import java.util.UUID;

import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.net.minecraft.entity.VanillaEntityType;
import rocks.cleanstone.net.minecraft.packet.data.EntityMetadata;
import rocks.cleanstone.net.minecraft.packet.outbound.SpawnMobPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

@Component
public class SummonCommand extends SimpleCommand {
    private final PlayerManager playerManager;

    @Autowired
    public SummonCommand(PlayerManager playerManager) {
        super("summon", Collections.emptyList(), VanillaEntityType.class);
        this.playerManager = playerManager;
    }

    @Override
    public void execute(CommandMessage message) {
        Player player = message.requireTargetPlayer();

        VanillaEntityType entityType = message.requireParameter(VanillaEntityType.class);
        double x = message.optionalParameter(Double.class)
                .orElseGet(() -> player.getEntity().getPosition().getX());
        double y = message.optionalParameter(Double.class)
                .orElseGet(() -> player.getEntity().getPosition().getY());
        double z = message.optionalParameter(Double.class)
                .orElseGet(() -> player.getEntity().getPosition().getZ());
        float yaw = message.optionalParameter(Float.class)
                .orElse(0.0f);
        float pitch = message.optionalParameter(Float.class)
                .orElse(0.0f);
        float headPitch = message.optionalParameter(Float.class)
                .orElse(0.0f);
        short velocityX = (short) (int) message.optionalParameter(Integer.class)
                .orElse(0);
        short velocityY = (short) (int) message.optionalParameter(Integer.class)
                .orElse(0);
        short velocityZ = (short) (int) message.optionalParameter(Integer.class)
                .orElse(0);

        playerManager.broadcastPacket(
                new SpawnMobPacket(
                        new Random().nextInt(Integer.MAX_VALUE),
                        UUID.randomUUID(),
                        entityType,
                        x, y, z,
                        yaw, pitch, headPitch,
                        velocityX, velocityY, velocityZ,
                        new EntityMetadata()
                )
        );
    }
}

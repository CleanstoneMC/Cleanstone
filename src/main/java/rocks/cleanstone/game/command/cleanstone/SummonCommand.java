package rocks.cleanstone.game.command.cleanstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.endpoint.cleanstone.entity.types.Chicken;
import rocks.cleanstone.endpoint.cleanstone.entity.types.SimpleChicken;
import rocks.cleanstone.endpoint.minecraft.vanilla.net.entity.VanillaMobType;
import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.game.world.World;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;
import rocks.cleanstone.utils.Vector;

import java.util.Collections;

@Component
public class SummonCommand extends SimpleCommand {
    private final PlayerManager playerManager;

    @Autowired
    public SummonCommand(PlayerManager playerManager) {
        super("summon", Collections.emptyList(), VanillaMobType.class);
        this.playerManager = playerManager;
    }

    @Override
    public void execute(CommandMessage message) {
        Player player = message.requireTargetPlayer();
        World world = player.getEntity().getWorld();
        VanillaMobType entityType = message.requireParameter(VanillaMobType.class);
        // TODO add more entity templates than just chicken
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
        HeadRotatablePosition position = new HeadRotatablePosition(new Position(x, y, z),
                new Rotation(yaw, pitch), new Rotation(0, headPitch));
        Vector velocity = new Vector(velocityX, velocityY, velocityZ);

        Chicken entity = new SimpleChicken(world, position, false, 5);
        world.getEntityRegistry().addEntity(entity);
    }
}

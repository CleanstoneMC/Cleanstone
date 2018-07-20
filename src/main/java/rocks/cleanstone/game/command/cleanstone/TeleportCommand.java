package rocks.cleanstone.game.command.cleanstone;

import java.util.Collections;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.entity.Rotation;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.PlayerMoveEvent;

public class TeleportCommand extends SimpleCommand {

    public TeleportCommand() {
        super("tp", Collections.singletonList("teleport"), Player.class, Player.class);
    }

    @Override
    public void execute(CommandMessage message) {
        Player p1 = message.requireTargetPlayer();
        RotatablePosition newPosition;
        String targetID;

        if (!message.nextParameterIs(Player.class)
                && !message.nextParameterIs(Double.class)
                && p1 != message.getCommandSender()
                && message.getCommandSender() instanceof Player) {
            // for /tp <destinationPlayer>
            Player p2 = p1;
            p1 = (Player) message.getCommandSender();
            newPosition = p2.getEntity().getPosition();
            targetID = p2.getName();
        } else if (message.nextParameterIs(Player.class)) {
            // for /tp <player> <destinationPlayer>
            Player p2 = message.requireParameter(Player.class);
            newPosition = p2.getEntity().getPosition();
            targetID = p2.getName();
        } else {
            // for /tp [player] <x> <y> <z> [yaw] [pitch]
            double x = message.requireParameter(Double.class);
            double y = message.requireParameter(Double.class);
            double z = message.requireParameter(Double.class);
            double yaw = message.optionalParameter(Double.class)
                    .orElse((double) p1.getEntity().getPosition().getRotation().getYaw());
            double pitch = message.optionalParameter(Double.class)
                    .orElse((double) p1.getEntity().getPosition().getRotation().getPitch());
            newPosition = new RotatablePosition(
                    new Position(x, y, z), new Rotation((float) yaw, (float) pitch));
            targetID = newPosition.toString();
        }

        p1.teleport(newPosition, PlayerMoveEvent.StandardMoveReason.CLIENT_REQUEST);
        if (p1 == message.getCommandSender()) {
            p1.sendMessage("game.command.cleanstone.teleported-self", targetID);
        } else {
            p1.sendMessage("game.command.cleanstone.teleported-by-other",
                    targetID, message.getCommandSender().getName());
            message.getCommandSender().sendMessage("game.command.cleanstone.teleported-other",
                    p1.getName(), targetID);
        }
    }
}

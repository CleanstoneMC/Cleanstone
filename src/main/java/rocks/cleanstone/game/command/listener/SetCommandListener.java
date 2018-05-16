package rocks.cleanstone.game.command.listener;

import rocks.cleanstone.game.chat.message.Chat;
import rocks.cleanstone.game.command.*;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.PlayerManager;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class SetCommandListener extends SimpleCommand {

    private final PlayerManager playerManager;

    public SetCommandListener(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    public String getCommandString() {
        return "set";
    }

    @Override
    public void execute(IssuedCommand issuedCommand) {
    }

    @Nullable
    @Override
    public Map<String, SubCommand> getSubCommands() {
        Map<String, SubCommand> subCommandMap = new HashMap<>();

        subCommandMap.put("health", new HealthCommand(this, playerManager));

        return subCommandMap;
    }

    private class HealthCommand extends SimpleSubCommand {
        private final SetCommandListener setCommandListener;
        private final PlayerManager playerManager;

        public HealthCommand(SetCommandListener setCommandListener, PlayerManager playerManager) {
            this.setCommandListener = setCommandListener;
            this.playerManager = playerManager;
        }

        @Override
        public Command getMainCommand() {
            return setCommandListener;
        }

        @Override
        public String getCommandString() {
            return "health";
        }

        @Override
        public int neededParameter() {
            return 1;
        }

        @Override
        public void execute(IssuedCommand issuedCommand) {
            String playerName = issuedCommand.getParameter().get(0);
            Player player = playerManager.getOnlinePlayer(playerName);

            if (player == null) {
                if (issuedCommand.getCommandSender() instanceof Player) {
                    ((Player) issuedCommand.getCommandSender()).sendMessage(new Chat("Player not found"));
                } else {
                    logger.info("Could not find Player \"{}\" for command \"{}\"", playerName, issuedCommand.getCommand());
                }

                return;
            }

            player.sendMessage(new Chat("Health set"));
        }
    }
}

package rocks.cleanstone.player.initialize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.game.command.completion.node.CommandNode;
import rocks.cleanstone.game.command.completion.node.NodeType;
import rocks.cleanstone.net.minecraft.packet.outbound.DeclareCommandsPacket;
import rocks.cleanstone.player.Player;
import rocks.cleanstone.player.event.AsyncPlayerInitializationEvent;

@Component
public class SendDeclareCommandsPacket {


    private final CommandRegistry commandRegistry;

    @Autowired
    public SendDeclareCommandsPacket(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    @Order(value = 35)
    @EventListener
    public void onInitialize(AsyncPlayerInitializationEvent e) {
        Player player = e.getPlayer();

        CommandNode rootNode = new CommandNode("", NodeType.ROOT);

        commandRegistry.getAllCommands().forEach(command -> {
            rootNode.getChildren().add(new CommandNode(command.getName(), NodeType.LITERAL));
        });

        DeclareCommandsPacket declareCommandsPacket = new DeclareCommandsPacket(rootNode);
        player.sendPacket(declareCommandsPacket);

    }
}

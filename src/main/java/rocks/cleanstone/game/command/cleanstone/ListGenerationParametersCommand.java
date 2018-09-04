package rocks.cleanstone.game.command.cleanstone;

import java.util.List;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.world.generation.WorldGenerator;

@Component
public class ListGenerationParametersCommand extends SimpleCommand {
    private final List<WorldGenerator> worldGenerators;

    public ListGenerationParametersCommand(List<WorldGenerator> worldGenerators) {
        super("listgenerationparameters");
        this.worldGenerators = worldGenerators;
    }

    @Override
    public void execute(CommandMessage commandMessage) {
       worldGenerators.forEach(wg -> {
            commandMessage.getCommandSender().sendRawMessage(wg.getName());
            wg.getGenerationParameters().forEach((key, value) -> {
                commandMessage.getCommandSender().sendRawMessage(
                        String.format("    %s: %s", key, value)
                );
            });
        });
    }
}

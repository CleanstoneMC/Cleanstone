package rocks.cleanstone.game.command.cleanstone;

import java.util.List;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.SimpleCommand;
import rocks.cleanstone.game.world.generation.WorldGenerationParameter;
import rocks.cleanstone.game.world.generation.WorldGenerator;

@Component
public class SetGenerationParameterCommand extends SimpleCommand {
    private final List<WorldGenerator> worldGenerators;

    public SetGenerationParameterCommand(List<WorldGenerator> worldGenerators) {
        super("setgenerationparameter", WorldGenerationParameter.class, Double.class);
        this.worldGenerators = worldGenerators;
    }

    @Override
    public void execute(CommandMessage commandMessage) {
        WorldGenerationParameter parameter = commandMessage.requireParameter(WorldGenerationParameter.class);
        double value = commandMessage.requireParameter(Double.class);

        worldGenerators.forEach(wg -> wg.setGenerationParameter(parameter, value));
    }
}

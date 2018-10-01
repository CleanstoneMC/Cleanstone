package rocks.cleanstone.game.command.cleanstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.SimpleCommand;

import java.util.Collections;

@Component
public class HelpCommand extends SimpleCommand {

    @Autowired
    public HelpCommand() {
        super("help", Collections.singletonList("?"), String.class);
    }
}

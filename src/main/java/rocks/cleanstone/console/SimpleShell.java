package rocks.cleanstone.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.shell.*;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.CommandMessageFactory;
import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.game.command.completion.CommandCompletion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Primary
@Component
public class SimpleShell extends Shell {

    private final ConsoleSender consoleSender;
    private ResultHandler resultHandler;
    private CommandRegistry commandRegistry;
    private final CommandCompletion commandCompletion;

    public SimpleShell(@Qualifier("mainResultHandler") ResultHandler resultHandler,
                       CommandRegistry commandRegistry,
                       CommandCompletion commandCompletion) {
        super(resultHandler);
        this.resultHandler = resultHandler;
        this.commandRegistry = commandRegistry;
        this.commandCompletion = commandCompletion;
        this.consoleSender = new ConsoleSender(resultHandler::handleResult);
    }

    /**
     * Evaluate a single "line" of input from the user by trying to map words to a command and
     * arguments.
     *
     * <p>
     * This method does not throw exceptions, it catches them and returns them as a regular
     * result
     * </p>
     *
     * @param input
     */
    @Override
    public Object evaluate(Input input) {
        if (noInput(input) || !isCommand(input.rawText())) {
            return NO_INPUT;
        }

        String commandString = input.rawText().substring(1);

        CommandMessage commandMessage = CommandMessageFactory.construct(consoleSender, input.rawText(), this.commandRegistry);

        Command command = commandRegistry.getCommand(commandMessage.getCommandName());
        if (command != null) {
            try {
                command.execute(commandMessage);
            } catch (Exception e) {
                return e;
            }
            return null;
        }

        return super.evaluate(() -> commandString);
    }

    /**
     * Gather completion proposals given some (incomplete) input the user has already typed
     * in. When and how this method is invoked is implementation specific and decided by the
     * actual user interface.
     */
    public List<CompletionProposal> complete(CompletionContext context) {
        if (!isCommand(context.upToCursor())) { //TODO: Do we want to autocomplete player names?
            return new ArrayList<>();
        }

        String commandString = context.upToCursor().substring(1);

        CompletionContext completionContext = new CompletionContext(Arrays.asList(commandString.split(" ")), context.getWordIndex(), context.getPosition() - 1);

        List<CompletionProposal> springShellCompletions = super.complete(completionContext).stream()
                .map(s -> "/" + s.value())
                .map(CompletionProposal::new)
                .collect(Collectors.toList());

        List<CompletionProposal> proposalList = new ArrayList<>();
        try {
            return commandCompletion.completeCommandLine(commandString, consoleSender)
                    .thenApply(completions ->
                            completions.stream()
                                    .map(String::trim)
                                    .map(CompletionProposal::new)
                                    .collect(Collectors.toList())
                    )
                    .thenApply(completions ->
                            Stream.of(completions, springShellCompletions)
                                    .flatMap(Collection::stream)
                                    .collect(Collectors.toList())
                    )
                    .get();
        } catch (InterruptedException | ExecutionException ignored) {
        }

        return proposalList;
    }

    /**
     * Return true if the parsed input ends up being empty (<em>e.g.</em> hitting ENTER on an
     * empty line or blank space).
     *
     * <p>
     * Also returns true (<em>i.e.</em> ask to ignore) when input starts with {@literal //},
     * which is used for comments.
     * </p>
     */
    private boolean noInput(Input input) {
        return input.words().isEmpty()
                || (input.words().size() == 1 && input.words().get(0).trim().isEmpty())
                || (input.words().iterator().next().matches("\\s*//.*"));
    }

    private boolean isCommand(String string) {
        return string != null && string.length() > 1 && string.charAt(0) == '/';
    }
}

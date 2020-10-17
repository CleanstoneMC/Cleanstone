package rocks.cleanstone.console;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.shell.Command.Help;
import org.springframework.shell.*;
import org.springframework.stereotype.Component;
import rocks.cleanstone.game.command.Command;
import rocks.cleanstone.game.command.CommandMessage;
import rocks.cleanstone.game.command.CommandMessageFactory;
import rocks.cleanstone.game.command.CommandRegistry;
import rocks.cleanstone.game.command.completion.CommandCompletion;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Primary
@Component
public class SimpleShell extends Shell {

    private final ConsoleSender consoleSender;
    private final CommandCompletion commandCompletion;
    private final CommandRegistry commandRegistry;

    @SuppressWarnings("unchecked")
    public SimpleShell(@Qualifier("mainResultHandler") ResultHandler resultHandler,
                       CommandRegistry commandRegistry,
                       CommandCompletion commandCompletion) {
        super(resultHandler);
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

        CommandMessage commandMessage = CommandMessageFactory.construct(consoleSender, input.rawText(), this.commandRegistry);

        Command command = commandRegistry.getCommand(commandMessage.getCommandName());
        if (command != null) {
            try {
                command.execute(commandMessage, true);
            } catch (Exception e) {
                return e;
            }
            return null;
        }

        return super.evaluate(() -> input.rawText().substring(1));
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

        List<CompletionProposal> springShellCompletions = super.complete(completionContext).parallelStream()
                .map(s -> "/" + s.value())
                .map(CompletionProposal::new)
                .collect(Collectors.toList());

        List<CompletionProposal> proposalList = new ArrayList<>();
        try {
            return commandCompletion.completeCommandLine(commandString, consoleSender)
                    // Trim the result from our command completion
                    .thenApply(completions ->
                            completions.parallelStream()
                                    .map(String::trim)
                                    .map(CompletionProposal::new)
                                    .collect(Collectors.toList())
                    )
                    // Map the Spring Shell Completion to a List
                    .thenApply(completions ->
                            Stream.of(completions, springShellCompletions)
                                    .flatMap(Collection::parallelStream)
                                    .collect(Collectors.toList())
                    )
                    .get();
        } catch (InterruptedException | ExecutionException ignored) {
        }

        return proposalList;
    }

    private List<Command> flatSubCommands(Command command) {
        return Stream.concat(
                Stream.of(command.getSubCommands().values()).flatMap(Collection::stream)
                        .map(this::flatSubCommands)
                        .filter(Objects::nonNull)
                        .flatMap(Collection::stream)
                , List.of(command).stream()
        ).collect(Collectors.toList());
    }

    private String getFullCommandName(Command command) {
        return "/" + Stream.of(
                command.getParents().stream().map(Command::getName).collect(Collectors.toList()),
                List.of(command.getName())
        ).flatMap(Collection::stream).collect(Collectors.joining(" "));
    }

    @Override
    public Map<String, MethodTarget> listCommands() {
        Map<String, MethodTarget> wrappedCommandList = commandRegistry.getAllCommands().parallelStream()
                .map(this::flatSubCommands)
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(this::getFullCommandName,
                        cmd -> MethodTarget.of("execute", new CommandWrapper(cmd), new Help(cmd.getUsage())))
                );

        Map<String, MethodTarget> springCommandList = super.listCommands().entrySet().stream()
                .map(entry -> Map.entry("/" + entry.getKey(), entry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return Stream.concat(wrappedCommandList.entrySet().stream(), springCommandList.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (cmd1, cmd2) -> cmd1));
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

    /**
     * Currently this only wraps it for beauty reasons. It never calls the execute {@link CommandWrapper#execute} function.
     */
    class CommandWrapper {
        private final Command command;

        public CommandWrapper(Command command) {
            this.command = command;
        }

        public Object execute(String message) {
            command.execute(CommandMessageFactory.construct(consoleSender, message, commandRegistry));
            return null;
        }
    }
}

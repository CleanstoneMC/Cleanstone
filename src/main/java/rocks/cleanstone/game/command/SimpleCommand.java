package rocks.cleanstone.game.command;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.chat.ConsoleSender;
import rocks.cleanstone.game.command.executor.CommandExecutor;
import rocks.cleanstone.game.command.executor.HelpPageExecutor;
import rocks.cleanstone.game.permission.Permission;
import rocks.cleanstone.player.Player;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class SimpleCommand implements Command {
    private final String name;
    private final List<String> aliases;
    private final Map<String, Command> subCommandMap = new HashMap<>();
    private final CommandExecutor commandExecutor;
    private final Class<?>[] expectedParameterTypes;
    private final Collection<Command> parents = new HashSet<>();

    public SimpleCommand(String name, Collection<String> aliases, CommandExecutor commandExecutor,
                         Class<?>... expectedParameterTypes) {
        this.name = name;
        this.aliases = new ArrayList<>(aliases);
        this.commandExecutor = commandExecutor;
        this.expectedParameterTypes = expectedParameterTypes;
    }

    public SimpleCommand(String name, List<String> aliases, Class<?>... expectedParameterTypes) {
        this(name, aliases, null, expectedParameterTypes);
    }

    public SimpleCommand(String name, CommandExecutor commandExecutor) {
        this(name, new ArrayList<>(), commandExecutor);
    }

    public SimpleCommand(String name, Class<?>... expectedParameterTypes) {
        this(name, Collections.emptyList(), expectedParameterTypes);
    }

    public SimpleCommand(String name, Collection<String> aliases) {
        this(name, aliases, null);
    }

    public SimpleCommand(String name) {
        this(name, new ArrayList<>(), new Class[0]);
    }

    @Override
    public Permission getPermission() {
        return null;
    }

    @Override
    public Map<String, Command> getSubCommands() {
        return ImmutableMap.copyOf(subCommandMap);
    }

    @Override
    public Command addSubCommand(Command subCommand, String... names) {
        for (String name : names) {
            subCommandMap.put(name, subCommand);
        }
        subCommandMap.put(subCommand.getName(), subCommand); //Add the subcommand by the name of the Command itself
        subCommand.getParents().add(this);
        return this;
    }

    @Override
    public boolean showInHelp() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean allowsPlayer() {
        return true;
    }

    @Override
    public boolean allowsConsole() {
        return true;
    }

    @Override
    public boolean allowsSender(MessageRecipient sender) {
        if (!allowsPlayer() && sender instanceof Player) {
            return false;
        }
        return allowsConsole() || !(sender instanceof ConsoleSender);
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public Class<?>[] getExpectedParameterTypes() {
        return expectedParameterTypes;
    }

    @Override
    public Collection<Command> getParents() {
        return parents;
    }

    @Override
    public String getUsage() {
        String text = CleanstoneServer.getMessage("game.command.invalid-usage.text"),
                number = CleanstoneServer.getMessage("game.command.invalid-usage.number");
        return "/" + getFullCommandName() + " " + Joiner.on(" ").join(
                Arrays.stream(getExpectedParameterTypes())
                        .map(Class::getSimpleName)
                        .map(name -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, name))
                        .map(name -> name.replace("string", text.toLowerCase())
                                .replaceAll("int|double", number.toLowerCase()))
                        .map(name -> "<" + name + ">")
                        .collect(Collectors.toList()));
    }

    private String getFullCommandName() {
        return Stream.of(
                this.getParents().stream().map(Command::getName).collect(Collectors.toList()),
                List.of(this.getName())
        ).flatMap(Collection::stream).collect(Collectors.joining(" "));
    }

    /**
     * Use {@link SimpleCommandRegistry#executeCommand} to execute commands
     */
    @Override
    public void execute(CommandMessage commandMessage) {
        if (commandExecutor != null) {
            commandExecutor.execute(commandMessage);
        } else {
            new HelpPageExecutor(this).execute(commandMessage);
        }
    }

    /**
     * Use {@link SimpleCommandRegistry#executeCommand} to execute commands
     */
    @Override
    public void execute(CommandMessage message, boolean considerSubCommands) {
        if (considerSubCommands && message.nextParameterIs(String.class)) {
            String parameter = message.requireParameter(String.class);
            Command subCommand = getSubCommands().get(parameter.toLowerCase(Locale.ENGLISH));
            if (subCommand != null) {
                subCommand.execute(message, true);
                return;
            }
            message.setParameterIndex(message.getParameterIndex() - 1);
        }
        execute(message);
    }
}
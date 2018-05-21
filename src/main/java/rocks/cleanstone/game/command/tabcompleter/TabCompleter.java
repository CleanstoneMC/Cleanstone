package rocks.cleanstone.game.command.tabcompleter;

public interface TabCompleter {
    Class getCompletingClass();

    String[] getCompletion();

    String[] getCompletion(String message);
}

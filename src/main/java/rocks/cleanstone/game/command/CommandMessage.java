package rocks.cleanstone.game.command;

import rocks.cleanstone.player.Player;

import java.util.List;

public interface CommandMessage {
    CommandSender getCommandSender();

    String getFullMessage();

    String getCommandName();

    List<String> getParameters();

    void setParameterIndex(int index);

    int getParameterIndex();

    Player requireTargetPlayer();

    <T> T requireParameter(Class<T> parameterClass);

    boolean isParameterPresent(Class<?> parameterClass);
}

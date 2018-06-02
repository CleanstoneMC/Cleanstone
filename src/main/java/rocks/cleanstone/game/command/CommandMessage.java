package rocks.cleanstone.game.command;

import java.util.Collection;
import java.util.List;

import rocks.cleanstone.player.Player;

public interface CommandMessage {
    CommandSender getCommandSender();

    String getFullMessage();

    String getCommandName();

    List<String> getParameters();

    int getParameterIndex();

    void setParameterIndex(int index);

    Player requireTargetPlayer();

    String requireStringMessage(boolean optional);

    <T> T requireParameter(Class<T> parameterClass);

    <T> Collection<T> requireVarargParameter(Class<T> parameterClass, boolean allowEmpty);

    boolean isParameterPresent(Class<?> parameterClass);
}

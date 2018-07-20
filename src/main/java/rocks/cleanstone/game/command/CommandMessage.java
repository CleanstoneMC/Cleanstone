package rocks.cleanstone.game.command;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.player.Player;

public interface CommandMessage {
    CommandSender getCommandSender();

    String getFullMessage();

    String getCommandName();

    List<String> getParameters();

    int getParameterIndex();

    void setParameterIndex(int index);

    <T> T requireParameter(Class<T> parameterClass);

    <T> Optional<T> optionalParameter(Class<T> parameterClass);

    <T> Collection<T> requireVarargParameter(Class<T> parameterClass, boolean allowEmpty);

    boolean nextParameterIs(Class<?> parameterClass);

    Player requireTargetPlayer();

    String requireStringMessage();

    Optional<String> optionalStringMessage();

    Text requireTextMessage();

    Optional<Text> optionalTextMessage();
}

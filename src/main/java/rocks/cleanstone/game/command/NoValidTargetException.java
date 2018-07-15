package rocks.cleanstone.game.command;

public class NoValidTargetException extends RuntimeException {
    private final MessageRecipient sender;

    public NoValidTargetException(MessageRecipient sender) {
        super("a target was neither specified, nor is " + sender.getClass().getSimpleName() + " a valid target");
        this.sender = sender;
    }

    public MessageRecipient getSender() {
        return sender;
    }
}

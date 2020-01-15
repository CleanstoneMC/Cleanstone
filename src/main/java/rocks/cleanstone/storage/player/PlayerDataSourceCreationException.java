package rocks.cleanstone.storage.player;

public class PlayerDataSourceCreationException extends Exception {
    public PlayerDataSourceCreationException() {
    }

    public PlayerDataSourceCreationException(String message) {
        super(message);
    }

    public PlayerDataSourceCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlayerDataSourceCreationException(Throwable cause) {
        super(cause);
    }

    public PlayerDataSourceCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package rocks.cleanstone.storage.world;

public class WorldDataSourceCreationException extends Exception {
    public WorldDataSourceCreationException() {
    }

    public WorldDataSourceCreationException(String message) {
        super(message);
    }

    public WorldDataSourceCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorldDataSourceCreationException(Throwable cause) {
        super(cause);
    }

    public WorldDataSourceCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

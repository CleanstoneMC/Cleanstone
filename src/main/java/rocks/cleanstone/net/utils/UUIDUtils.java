package rocks.cleanstone.net.utils;

import java.util.UUID;

public class UUIDUtils {

    private UUIDUtils() {
    }

    public static UUID fromStringWithoutHyphens(String str) {
        return new UUID(Long.parseUnsignedLong(str.substring(0, 16), 16),
                Long.parseUnsignedLong(str.substring(16), 16));
    }

    public static String toStringWithoutHyphens(UUID uuid) {
        return uuid.toString().replace("-", "");
    }
}

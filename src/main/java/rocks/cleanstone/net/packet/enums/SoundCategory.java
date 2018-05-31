package rocks.cleanstone.net.packet.enums;

public enum SoundCategory {
    MASTER(0, "master"),
    MUSIC(1, "music"),
    RECORDS(2, "record"),
    WEATHER(3, "weather"),
    BLOCKS(4, "block"),
    HOSTILE(5, "hostile"),
    NEUTRAL(6, "neutral"),
    PLAYERS(7, "player"),
    AMBIENT(8, "ambient"),
    VOICE(9, "voice");

    private final int categoryID;
    private final String categoryName;

    SoundCategory(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    @SuppressWarnings("Duplicates")
    public static SoundCategory fromCategoryID(int categoryID) {
        for (SoundCategory soundCategory : SoundCategory.values()) {
            if (soundCategory.getCategoryID() == categoryID) {
                return soundCategory;
            }
        }

        return null;
    }

    @SuppressWarnings("Duplicates")
    public static SoundCategory fromCategoryName(String categoryName) {
        for (SoundCategory soundCategory : SoundCategory.values()) {
            if (soundCategory.getCategoryName().equals(categoryName)) {
                return soundCategory;
            }
        }

        return null;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

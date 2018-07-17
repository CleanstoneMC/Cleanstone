package rocks.cleanstone.game;

public interface Identifiable {

    Identity getID();

    /**
     * @return A nice and formatted name, use {@link Identity#getName} for the actual name
     */
    String getName();
}

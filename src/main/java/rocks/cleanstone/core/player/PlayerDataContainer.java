package rocks.cleanstone.core.player;

public interface PlayerDataContainer {
    <T extends PlayerData> T getPlayerData(Class<T> clazz);

    <T extends PlayerData> void addPlayerData(Class<T> clazz);

    <T extends PlayerData> void removePlayerData(Class<T> clazz);
}

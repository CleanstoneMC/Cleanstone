package rocks.cleanstone.net.minecraft.login;

public class LoginData {

    private final byte[] verifyToken;

    private final String playerName;

    public LoginData(byte[] verifyToken, String playerName) {
        this.verifyToken = verifyToken;
        this.playerName = playerName;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

    public String getPlayerName() {
        return playerName;
    }
}

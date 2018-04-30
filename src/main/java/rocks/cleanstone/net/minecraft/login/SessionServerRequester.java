package rocks.cleanstone.net.minecraft.login;

import com.google.common.base.Charsets;
import com.google.gson.Gson;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

import rocks.cleanstone.net.Connection;

public class SessionServerRequester {

    private static final String SESSION_SERVER_URL = "https://sessionserver.mojang.com/session/minecraft/hasJoined";

    private final LoginManager loginManager;

    public SessionServerRequester(LoginManager loginManager) {
        this.loginManager = loginManager;
    }

    @Async
    public AsyncResult<SessionServerResponse> request(Connection connection, LoginData loginData) {
        try {
            String authHash = generateAuthHash(connection, loginData);
            URL url = new URL(SESSION_SERVER_URL
                    + String.format("?username=%s&serverId=%s&ip=%s", loginData.getPlayerName(), authHash,
                    URLEncoder.encode(connection.getAddress().getHostAddress(), "UTF-8")));
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            try (Reader reader = new InputStreamReader(con.getInputStream(), Charsets.UTF_8)) {
                Gson gson = new Gson();
                return new AsyncResult<>(gson.fromJson(reader, SessionServerResponse.class));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to contact session servers", e);
        }
    }

    private String generateAuthHash(Connection connection, LoginData loginData) {
        String hash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update("".getBytes(Charsets.US_ASCII)); // Server ID is empty
            digest.update(connection.getSharedSecret().getEncoded());
            digest.update(loginManager.getNetworking().getKeyPair().getPublic().getEncoded());
            hash = new BigInteger(digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to generate authentication hash", e);
        }
        return hash;
    }
}

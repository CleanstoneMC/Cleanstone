package rocks.cleanstone.net.minecraft.login;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import javax.net.ssl.HttpsURLConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.net.Connection;

@Slf4j
@Component
public class SimpleSessionServerRequester implements SessionServerRequester {
    private static final String SESSION_SERVER_URL = "https://sessionserver.mojang.com/session/minecraft/hasJoined";

    @Async(value = "mcLoginExec")
    public ListenableFuture<SessionServerResponse> request(Connection connection, LoginData loginData,
                                                           PublicKey publicKey) {
        try {
            final String authHash = LoginCrypto.generateAuthHash(connection.getSharedSecret(), publicKey);
            final URL url = new URL(SESSION_SERVER_URL
                    + String.format("?username=%s&serverId=%s&ip=%s", loginData.getPlayerName(), authHash,
                    URLEncoder.encode(connection.getAddress().getHostAddress(), String.valueOf(StandardCharsets.UTF_8))));
            final HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            try (final Reader reader = new InputStreamReader(con.getInputStream(), Charsets.UTF_8)) {
                final Gson gson = new Gson();
                return new AsyncResult<>(gson.fromJson(reader, SessionServerResponse.class));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to contact session servers", e);
        }
    }
}

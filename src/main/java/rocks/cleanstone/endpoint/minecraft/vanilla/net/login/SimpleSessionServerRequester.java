package rocks.cleanstone.endpoint.minecraft.vanilla.net.login;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.net.Connection;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.security.PublicKey;

@Slf4j
@Component
public class SimpleSessionServerRequester implements SessionServerRequester {
    private static final String SESSION_SERVER_URL = "https://sessionserver.mojang.com/session/minecraft/hasJoined";

    @Async(value = "mcLoginExec")
    public ListenableFuture<SessionServerResponse> request(Connection connection, LoginData loginData,
                                                           PublicKey publicKey) {
        try {
            String authHash = LoginCrypto.generateAuthHash(connection.getSharedSecret(), publicKey);
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
}

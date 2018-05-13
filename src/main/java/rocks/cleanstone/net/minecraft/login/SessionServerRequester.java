package rocks.cleanstone.net.minecraft.login;

import org.springframework.util.concurrent.ListenableFuture;
import rocks.cleanstone.net.Connection;

import java.security.PublicKey;

public interface SessionServerRequester {
    ListenableFuture<SessionServerResponse> request(Connection connection, LoginData loginData, PublicKey publicKey);
}

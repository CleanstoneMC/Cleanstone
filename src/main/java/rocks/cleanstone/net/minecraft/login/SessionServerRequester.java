package rocks.cleanstone.net.minecraft.login;

import org.springframework.util.concurrent.ListenableFuture;

import java.security.PublicKey;

import rocks.cleanstone.net.Connection;

public interface SessionServerRequester {
    ListenableFuture<SessionServerResponse> request(Connection connection, LoginData loginData, PublicKey publicKey);
}

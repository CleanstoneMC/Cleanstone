package rocks.cleanstone.endpoint.minecraft.java.net;

import com.google.common.collect.Maps;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.endpoint.minecraft.java.net.login.event.AsyncLoginSuccessEvent;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.inbound.InKeepAlivePacket;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.DisconnectPacket;
import rocks.cleanstone.endpoint.minecraft.java.net.packet.outbound.OutKeepAlivePacket;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.Connection;
import rocks.cleanstone.net.event.ConnectionClosedEvent;
import rocks.cleanstone.net.event.InboundPacketEvent;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class KeepAliveTask {

    private static final long CLIENT_RESPONSE_TIMEOUT = 30 * 1000L;

    private final Map<Connection, Long> connectionLastResponseMap = Maps.newConcurrentMap();
    private final Map<Connection, Long> connectionLastKeepAliveIDMap = Maps.newConcurrentMap();

    @Scheduled(fixedRate = 10 * 1000, initialDelay = 10 * 1000)
    public void sendKeepAliveRequests() {
        connectionLastResponseMap.forEach((connection, lastResponseTime) -> {

            if ((System.currentTimeMillis() - lastResponseTime) > CLIENT_RESPONSE_TIMEOUT) {
                connection.close(new DisconnectPacket(Text.of(
                        CleanstoneServer.getMessage("net.minecraft.client-timeout"))));
            } else {
                connection.sendPacket(new OutKeepAlivePacket(connectionLastKeepAliveIDMap.get(connection)));
            }
        });
    }

    @Async
    @EventListener
    public void onKeepAliveReceive(InboundPacketEvent<InKeepAlivePacket> e) {
        InKeepAlivePacket packet = e.getPacket();
        long keepAliveID = packet.getKeepAliveID();

        if (connectionLastKeepAliveIDMap.getOrDefault(e.getConnection(), -1L) == keepAliveID) {
            connectionLastResponseMap.put(e.getConnection(), System.currentTimeMillis());
            generateNewKeepAliveID(e.getConnection());
        }
    }

    @EventListener
    public void onLoginSuccess(AsyncLoginSuccessEvent e) {
        connectionLastResponseMap.put(e.getConnection(), System.currentTimeMillis());
        generateNewKeepAliveID(e.getConnection());
    }

    @EventListener
    public void onDisconnect(ConnectionClosedEvent e) {
        connectionLastResponseMap.remove(e.getConnection());
        connectionLastKeepAliveIDMap.remove(e.getConnection());
    }

    private void generateNewKeepAliveID(Connection connection) {
        long id = ThreadLocalRandom.current().nextLong();
        connectionLastKeepAliveIDMap.put(connection, id);
    }
}

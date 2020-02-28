package rocks.cleanstone.core.config.structs;

import lombok.Data;

import java.net.InetAddress;

@Data
public class NetworkConfig {
    private int port;
    private InetAddress address;
}

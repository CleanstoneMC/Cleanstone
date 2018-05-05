package rocks.cleanstone.core.player;

import java.net.InetAddress;

import rocks.cleanstone.net.minecraft.packet.data.Text;
import rocks.cleanstone.net.packet.Packet;

public interface Player {
    PlayerID getId();
    void sendPacket(Packet packet);
    InetAddress getAddress();
    void kick(Text reason);
}

package rocks.cleanstone.core.player;

import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.entity.vanilla.SimpleHuman;
import rocks.cleanstone.net.minecraft.packet.data.Text;
import rocks.cleanstone.net.packet.Packet;

import java.net.InetAddress;

public interface Player {

    PlayerID getId();

    void sendPacket(Packet packet);

    InetAddress getAddress();

    void kick(Text reason);

    Human getEntity();

    void setEntity(Human entity);
}

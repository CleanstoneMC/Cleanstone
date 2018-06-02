package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class DisconnectPacket implements Packet {

    private final Text reason;

    public DisconnectPacket(Text reason) {
        this.reason = reason;
    }

    public Text getReason() {
        return reason;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.DISCONNECT;
    }
}

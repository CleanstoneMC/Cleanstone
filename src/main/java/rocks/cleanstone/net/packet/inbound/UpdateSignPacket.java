package rocks.cleanstone.net.packet.inbound;

import rocks.cleanstone.game.Position;
import rocks.cleanstone.net.packet.MinecraftInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class UpdateSignPacket implements Packet {

    private final Position location;
    private final String line1;
    private final String line2;
    private final String line3;
    private final String line4;

    public UpdateSignPacket(Position location, String line1, String line2, String line3, String line4) {
        this.location = location;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
    }

    public Position getLocation() {
        return location;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getLine3() {
        return line3;
    }

    public String getLine4() {
        return line4;
    }

    @Override
    public PacketType getType() {
        return MinecraftInboundPacketType.UPDATE_SIGN;
    }
}

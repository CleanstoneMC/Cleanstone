package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class SetCommandsEnabledPacket implements Packet {

    private final boolean enabled;

    public SetCommandsEnabledPacket(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SET_COMMANDS_ENABLED;
    }
}


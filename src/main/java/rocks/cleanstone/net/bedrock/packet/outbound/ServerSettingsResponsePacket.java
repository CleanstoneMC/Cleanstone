package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ServerSettingsResponsePacket implements Packet {

    private final long formId;
    private final String data;

    public ServerSettingsResponsePacket(long formId, String data) {
        this.formId = formId;
        this.data = data;
    }

    public long getFormId() {
        return formId;
    }

    public String getData() {
        return data;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.SERVER_SETTINGS_RESPONSE;
    }
}


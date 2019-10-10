package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ModalFormResponsePacket implements Packet {

    private final int formId;
    private final String data;

    public ModalFormResponsePacket(int formId, String data) {
        this.formId = formId;
        this.data = data;
    }

    public int getFormId() {
        return formId;
    }

    public String getData() {
        return data;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.MODAL_FORM_RESPONSE;
    }
}


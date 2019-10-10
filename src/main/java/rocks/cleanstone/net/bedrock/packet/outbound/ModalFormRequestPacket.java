package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ModalFormRequestPacket implements Packet {

    private final int formId;
    private final String data;

    public ModalFormRequestPacket(int formId, String data) {
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
        return BedrockOutboundPacketType.MODAL_FORM_REQUEST;
    }
}


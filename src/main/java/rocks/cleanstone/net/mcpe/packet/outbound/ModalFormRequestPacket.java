package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
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
        return MCPEOutboundPacketType.MODAL_FORM_REQUEST;
    }
}


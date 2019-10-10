package rocks.cleanstone.net.bedrock.packet.outbound;

import rocks.cleanstone.net.bedrock.packet.BedrockOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class GuiDataPickItemPacket implements Packet {


    public GuiDataPickItemPacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.GUI_DATA_PICK_ITEM;
    }
}


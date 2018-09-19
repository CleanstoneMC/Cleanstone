package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class GuiDataPickItemPacket implements Packet {


    public GuiDataPickItemPacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.GUI_DATA_PICK_ITEM;
    }
}


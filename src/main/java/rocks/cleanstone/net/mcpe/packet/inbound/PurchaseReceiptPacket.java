package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PurchaseReceiptPacket implements Packet {


    public PurchaseReceiptPacket() {

    }


    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.PURCHASE_RECEIPT;
    }
}


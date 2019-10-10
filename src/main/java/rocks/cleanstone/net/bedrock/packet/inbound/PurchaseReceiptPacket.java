package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PurchaseReceiptPacket implements Packet {


    public PurchaseReceiptPacket() {

    }


    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.PURCHASE_RECEIPT;
    }
}


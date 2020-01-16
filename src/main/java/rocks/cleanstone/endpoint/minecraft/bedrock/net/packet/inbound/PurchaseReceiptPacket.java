package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
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


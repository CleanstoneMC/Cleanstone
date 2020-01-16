package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.inbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockInboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.Transaction;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InventoryTransactionPacket implements Packet {

    private final Transaction transaction;

    public InventoryTransactionPacket(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public PacketType getType() {
        return BedrockInboundPacketType.INVENTORY_TRANSACTION;
    }
}


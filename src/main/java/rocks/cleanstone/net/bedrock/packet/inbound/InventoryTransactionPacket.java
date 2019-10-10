package rocks.cleanstone.net.bedrock.packet.inbound;

import rocks.cleanstone.net.bedrock.packet.BedrockInboundPacketType;
import rocks.cleanstone.net.bedrock.packet.data.Transaction;
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


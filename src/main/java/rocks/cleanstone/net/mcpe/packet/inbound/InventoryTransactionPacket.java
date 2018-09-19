package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.Transaction;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class InventoryTransactionPacket implements Packet {

    private final Transaction transaction;

    public InventoryTransactionPacket(Transaction transaction) {
        this.transaction =  transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.INVENTORY_TRANSACTION;
    }
}


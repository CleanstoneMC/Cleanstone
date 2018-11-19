package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

import java.util.UUID;

public class CommandRequestPacket implements Packet {

    private final String command;
    private final int commandType;
    private final UUID unknownUUID;
    private final String requestID;
    private final boolean unknown;

    public CommandRequestPacket(String command, int commandType, UUID unknownUUID, String requestID, boolean unknown) {
        this.command = command;
        this.commandType = commandType;
        this.unknownUUID = unknownUUID;
        this.requestID = requestID;
        this.unknown = unknown;
    }

    public String getCommand() {
        return command;
    }

    public int getCommandType() {
        return commandType;
    }

    public UUID getUnknownUUID() {
        return unknownUUID;
    }

    public String getRequestID() {
        return requestID;
    }

    public boolean getUnknown() {
        return unknown;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.COMMAND_REQUEST;
    }
}


package rocks.cleanstone.net.mcpe.packet.inbound;

import rocks.cleanstone.net.mcpe.packet.MCPEInboundPacketType;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class PhotoTransferPacket implements Packet {

    private final String fileName;
    private final String imageData;
    private final String unknown2;

    public PhotoTransferPacket(String fileName, String imageData, String unknown2) {
        this.fileName = fileName;
        this.imageData = imageData;
        this.unknown2 = unknown2;
    }

    public String getFileName() {
        return fileName;
    }

    public String getImageData() {
        return imageData;
    }

    public String getUnknown2() {
        return unknown2;
    }

    @Override
    public PacketType getType() {
        return MCPEInboundPacketType.PHOTO_TRANSFER;
    }
}


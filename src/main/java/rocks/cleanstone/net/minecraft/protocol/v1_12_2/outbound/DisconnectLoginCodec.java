package rocks.cleanstone.net.minecraft.protocol.v1_12_2.outbound;

import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.packet.protocol.ProtocolState;

public class DisconnectLoginCodec extends DisconnectCodec {

    @Override
    public int getProtocolPacketID() {
        return 0x00;
    }

    @Override
    public ProtocolState getProtocolState() {
        return VanillaProtocolState.LOGIN;
    }
}

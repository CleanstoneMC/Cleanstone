package rocks.cleanstone.net.minecraft.protocol.v1_13.outbound;

import rocks.cleanstone.net.minecraft.protocol.VanillaProtocolState;
import rocks.cleanstone.net.protocol.ProtocolState;

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

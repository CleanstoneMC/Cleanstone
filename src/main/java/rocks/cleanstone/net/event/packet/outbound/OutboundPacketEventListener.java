package rocks.cleanstone.net.event.packet.outbound;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rocks.cleanstone.net.event.OutboundPacketEvent;
import rocks.cleanstone.net.packet.Packet;

public abstract class OutboundPacketEventListener<T extends Packet> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public abstract void onPacket(OutboundPacketEvent<T> outboundPacketEvent);
}

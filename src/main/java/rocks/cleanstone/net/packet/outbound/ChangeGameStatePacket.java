package rocks.cleanstone.net.packet.outbound;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.enums.GameStateChangeReason;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class ChangeGameStatePacket implements Packet {

    private final GameStateChangeReason reason;
    private final float value;

    public ChangeGameStatePacket(int reason, float value) {
        this.reason = GameStateChangeReason.fromReasonID(reason);
        this.value = value;
    }

    public ChangeGameStatePacket(GameStateChangeReason reason, float value) {
        this.reason = reason;
        this.value = value;
    }

    public GameStateChangeReason getReason() {
        return reason;
    }

    public float getValue() {
        return value;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.CHANGE_GAME_STATE;
    }
}

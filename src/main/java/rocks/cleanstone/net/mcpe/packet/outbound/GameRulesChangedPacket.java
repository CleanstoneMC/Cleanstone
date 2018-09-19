package rocks.cleanstone.net.mcpe.packet.outbound;

import rocks.cleanstone.net.mcpe.packet.MCPEOutboundPacketType;
import rocks.cleanstone.net.mcpe.packet.data.GameRules;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class GameRulesChangedPacket implements Packet {

    private final GameRules rules;

    public GameRulesChangedPacket(GameRules rules) {
        this.rules =  rules;
    }

    public GameRules getRules() {
        return rules;
    }

    @Override
    public PacketType getType() {
        return MCPEOutboundPacketType.GAME_RULES_CHANGED;
    }
}


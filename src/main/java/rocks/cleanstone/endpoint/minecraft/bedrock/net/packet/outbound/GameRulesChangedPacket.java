package rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.outbound;

import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.BedrockOutboundPacketType;
import rocks.cleanstone.endpoint.minecraft.bedrock.net.packet.data.GameRules;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class GameRulesChangedPacket implements Packet {

    private final GameRules rules;

    public GameRulesChangedPacket(GameRules rules) {
        this.rules = rules;
    }

    public GameRules getRules() {
        return rules;
    }

    @Override
    public PacketType getType() {
        return BedrockOutboundPacketType.GAME_RULES_CHANGED;
    }
}


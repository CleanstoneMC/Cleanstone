package rocks.cleanstone.net.packet.minecraft.outbound;

import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.minecraft.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.Animation;

public class AnimationPacket implements Packet {

    private final int entityID;
    private final Animation animation;

    public AnimationPacket(int entityID, byte animation) {
        this.entityID = entityID;
        this.animation = Animation.fromAnimationID(animation);
    }

    public AnimationPacket(int entityID, Animation animation) {
        this.entityID = entityID;
        this.animation = animation;
    }

    public int getEntityID() {
        return entityID;
    }

    public Animation getAnimation() {
        return animation;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.ANIMATION;
    }
}

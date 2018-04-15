package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.Animation;

public class AnimationPacket extends SendPacket {

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
        return MinecraftSendPacketType.ANIMATION;
    }
}

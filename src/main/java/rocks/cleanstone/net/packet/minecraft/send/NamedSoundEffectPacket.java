package rocks.cleanstone.net.packet.minecraft.send;

import rocks.cleanstone.net.packet.PacketType;
import rocks.cleanstone.net.packet.SendPacket;
import rocks.cleanstone.net.packet.minecraft.MinecraftSendPacketType;
import rocks.cleanstone.net.packet.minecraft.enums.SoundCategory;

public class NamedSoundEffectPacket extends SendPacket {

    private final String soundName;
    private final SoundCategory soundCategory;
    private final int x;
    private final int y;
    private final int z;
    private final float volume;
    private final float pitch;

    public NamedSoundEffectPacket(String soundName, int soundCategory, int x, int y, int z, float volume, float pitch) {
        this.soundName = soundName;
        this.soundCategory = SoundCategory.fromCategoryID(soundCategory);//TODO
        this.x = x;
        this.y = y;
        this.z = z;
        this.volume = volume;
        this.pitch = pitch;
    }

    public NamedSoundEffectPacket(String soundName, SoundCategory soundCategory, int x, int y, int z, float volume, float pitch) {
        this.soundName = soundName;
        this.soundCategory = soundCategory;
        this.x = x;
        this.y = y;
        this.z = z;
        this.volume = volume;
        this.pitch = pitch;
    }

    public String getSoundName() {
        return soundName;
    }

    public SoundCategory getSoundCategory() {
        return soundCategory;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public PacketType getType() {
        return MinecraftSendPacketType.NAMED_SOUND_EFFECT;
    }
}

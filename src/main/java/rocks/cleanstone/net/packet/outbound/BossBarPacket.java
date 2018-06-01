package rocks.cleanstone.net.packet.outbound;

import java.util.UUID;

import rocks.cleanstone.net.packet.MinecraftOutboundPacketType;
import rocks.cleanstone.net.packet.enums.BossBarAction;
import rocks.cleanstone.net.packet.enums.BossBarColor;
import rocks.cleanstone.net.packet.enums.BossBarDivision;
import rocks.cleanstone.net.packet.enums.BossBarFlag;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.PacketType;

public class BossBarPacket implements Packet {

    private final UUID uuid;
    private final BossBarAction bossBarAction;

    private final String title;
    private final float health;
    private final BossBarColor bossBarColor;
    private final BossBarDivision division;
    private final BossBarFlag[] flags;

    /**
     * Action 0 Constructor
     */
    public BossBarPacket(UUID uuid, BossBarAction bossBarAction, String title, float health, int color, int division, byte flags) {
        this.uuid = uuid;
        this.bossBarAction = bossBarAction;
        this.title = title;
        this.health = health;
        this.bossBarColor = BossBarColor.fromColorID(color);
        this.division = BossBarDivision.fromDivisionID(division);
        this.flags = BossBarFlag.fromBitMask(flags);
    }

    public BossBarPacket(UUID uuid, BossBarAction bossBarAction, String title, float health, BossBarColor bossBarColor, BossBarDivision division, BossBarFlag[] flags) {
        this.uuid = uuid;
        this.bossBarAction = bossBarAction;
        this.title = title;
        this.health = health;
        this.bossBarColor = bossBarColor;
        this.division = division;
        this.flags = flags;
    }

    /**
     * Action 1 Constructor
     */
    public BossBarPacket(UUID uuid, BossBarAction bossBarAction) {
        this.uuid = uuid;
        this.bossBarAction = bossBarAction;
        title = null;
        health = -1;
        bossBarColor = null;
        division = null;
        flags = null;
    }

    /**
     * Action 2 Constructor
     */
    public BossBarPacket(UUID uuid, BossBarAction bossBarAction, float health) {
        this.uuid = uuid;
        this.bossBarAction = bossBarAction;
        this.health = health;
        title = null;
        bossBarColor = null;
        division = null;
        flags = null;
    }

    /**
     * Action 3 Constructor
     */
    public BossBarPacket(UUID uuid, BossBarAction bossBarAction, int color, int division) {
        this.uuid = uuid;
        this.bossBarAction = bossBarAction;
        this.bossBarColor = BossBarColor.fromColorID(color);
        this.division = BossBarDivision.fromDivisionID(division);
        title = null;
        health = -1;
        flags = null;
    }

    public BossBarPacket(UUID uuid, BossBarAction bossBarAction, BossBarColor bossBarColor, int division) {
        this.uuid = uuid;
        this.bossBarAction = bossBarAction;
        this.bossBarColor = bossBarColor;
        this.division = BossBarDivision.fromDivisionID(division);
        title = null;
        health = -1;
        flags = null;
    }

    /**
     * Action 4 Constructor
     */
    public BossBarPacket(UUID uuid, BossBarAction bossBarAction, byte flags) {
        this.uuid = uuid;
        this.bossBarAction = bossBarAction;
        this.flags = BossBarFlag.fromBitMask(flags);
        title = null;
        health = -1;
        bossBarColor = null;
        division = null;
    }

    public UUID getUuid() {
        return uuid;
    }

    public BossBarAction getBossBarAction() {
        return bossBarAction;
    }

    public String getTitle() {
        return title;
    }

    public float getHealth() {
        return health;
    }

    public BossBarColor getBossBarColor() {
        return bossBarColor;
    }

    public BossBarDivision getDivision() {
        return division;
    }

    public BossBarFlag[] getFlags() {
        return flags;
    }

    @Override
    public PacketType getType() {
        return MinecraftOutboundPacketType.BOSS_BAR;
    }
}
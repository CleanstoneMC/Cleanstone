package rocks.cleanstone.net.minecraft.entity.data;

import com.sun.org.apache.xpath.internal.operations.Bool;
import rocks.cleanstone.net.minecraft.entity.metadata.types.*;
import rocks.cleanstone.net.minecraft.entity.metadata.types.Boolean;
import rocks.cleanstone.net.minecraft.entity.metadata.types.Byte;
import rocks.cleanstone.net.minecraft.entity.metadata.types.Float;
import rocks.cleanstone.net.minecraft.entity.metadata.types.Position;

import javax.annotation.Nullable;

import static rocks.cleanstone.net.minecraft.entity.data.ProtocolEntityMetadataEntry.entryOf;
import static rocks.cleanstone.net.minecraft.entity.metadata.EntityMetadataType.*;

public enum ProtocolEntityData {
    ENTITY("entity", null, entryOf("entityBits", 1, BYTE, Byte.of(0)), entryOf("airAmount", 1, VAR_INT, VarInt.of(300)), entryOf("customName", 2, OPTIONAL_CHAT, OptionalChat.of()), entryOf("customNameVisible", 3, BOOLEAN, Boolean.of(false)), entryOf("silent", 4, BOOLEAN, Boolean.of(false)), entryOf("noGravity", 5, BOOLEAN, Boolean.of(false))),
    THROWABLE("throwable", ENTITY),
    EGG("egg", THROWABLE),
    ENDER_PEARL("ender_pearl", THROWABLE),
    EXPERIENCE_BOTTLE("experience_bottle", THROWABLE),
    SNOWBALL("snowball", THROWABLE),
    POTION("potion", THROWABLE, entryOf("potionType", 6, SLOT, Slot.of())),
    EYE_OF_ENDER("eye_of_ender", ENTITY),
    FALLING_BLOCK("falling_block", ENTITY, entryOf("spawnPosition", 6, POSITION, Position.of())),
    AREA_EFFECT_CLOUD("area_effect_cloud", ENTITY, entryOf("radius", 6, FLOAT, Float.of(0.5f)), entryOf("color", 7, VAR_INT, VarInt.of(0)), entryOf("ignoreRadius", 8, BOOLEAN, Boolean.of(false))),// TODO: Particle? , entryOf("particle", )),
    FISHING_HOOK("fishing_hook", ENTITY, entryOf("hookedEntity", 6, VAR_INT, VarInt.of(0))),
    ARROW("arrow", ENTITY, entryOf("hookedEntity", 6, BYTE, Byte.of(0)), entryOf("shooterId", 7, OPTIONAL_UUID, OptionalUUID.of())),
    TIPPED_ARROW("tipped_arrow", ARROW, entryOf("color", 8, VAR_INT, VarInt.of(-1))),
    TRIDENT("trident", ARROW, entryOf("loyalty", 8, VAR_INT, VarInt.of(0))),
    BOAT("boat", ENTITY, entryOf("lastHit", 6, VAR_INT, VarInt.of(0)), entryOf("forwardDirection", 6, VAR_INT, VarInt.of(0)), entryOf("damageTaken", 8, FLOAT, Float.of(0.0f)), entryOf("type", 9, VAR_INT, VarInt.of(0)), entryOf("leftPaddle", 10, BOOLEAN, Boolean.of(false)), entryOf("rightPaddle", 11, BOOLEAN, Boolean.of(false)), entryOf("spashTimer", 12, VAR_INT, VarInt.of(0))),
    ENDER_CRYSTAL("ender_crystal", ENTITY, entryOf("beamTarget", 6, OPTIONAL_POSITION, OptionalPosition.of()), entryOf("showBottom", 7, BOOLEAN, Boolean.of(true))),
    ABSTRACT_FIREBALL("abstract_fireball", ENTITY),
    DRAGON_FIREBALL("dragon_fireball", ABSTRACT_FIREBALL),
    FIREBALL("fireball", ABSTRACT_FIREBALL),
    SMALL_FIREBALL("small_fireball", ABSTRACT_FIREBALL),
    WITHER_SKULL("wither_skull", ABSTRACT_FIREBALL, entryOf("invulnerable", 6, BOOLEAN, Boolean.of(false))),
    FIREWORKS("fireworks", ENTITY, entryOf("info", 6, SLOT, Slot.of()), entryOf("entityId", 7, VAR_INT, VarInt.of(0))),
    HANGING("hanging", ENTITY),
    ITEM_FRAME("item_frame", HANGING),
    ITEM("item", ENTITY, entryOf("item", 6, SLOT, Slot.of())),

    LIVING("living", ENTITY, entryOf("handState", 6, BYTE, Byte.of(0)), entryOf("health", 7, FLOAT, Float.of(1.0f)), entryOf("potionEffectColor", 8, VAR_INT, VarInt.of(0)), entryOf("potionEffectAmbient", 9, BOOLEAN, Boolean.of(false)), entryOf("arrowAmount", 10, VAR_INT, VarInt.of(0))),

    MOB("mob", LIVING, entryOf("mobBits", 11, BYTE, Byte.of(0))),
    AGEABLE("ageable", MOB, entryOf("isBaby", 12, BOOLEAN, Boolean.of(false))),
    CHICKEN("chicken", AGEABLE),
    SHEEP("sheep", AGEABLE, entryOf("sheepBits", 13, BYTE, Byte.of(0))),
    BLAZE("blaze", MOB, entryOf("blazeBits", 12, BYTE, Byte.of(0))),
    CREEPER("creeper", MOB, entryOf("creeperState", 12, VAR_INT, VarInt.of(0)), entryOf("charged", 13, BOOLEAN, Boolean.of(false)), entryOf("ignited", 14, BOOLEAN, Boolean.of(false))),
    ;

    private final String id;
    private final ProtocolEntityData ancestor;
    private final ProtocolEntityMetadataEntry[] metadata;

    ProtocolEntityData(String id, ProtocolEntityData ancestor, ProtocolEntityMetadataEntry... metadata) {
        this.id = id;
        this.ancestor = ancestor;
        this.metadata = metadata;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public ProtocolEntityData getAncestor() {
        return ancestor;
    }

    public ProtocolEntityMetadataEntry[] getMetadataEntries() {
        return metadata;
    }
}

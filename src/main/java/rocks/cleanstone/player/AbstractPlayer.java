package rocks.cleanstone.player;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Identity;
import rocks.cleanstone.game.chat.ChatMode;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.entity.cleanstone.Human;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.gamemode.vanilla.VanillaGameMode;
import rocks.cleanstone.game.inventory.MainHandSide;
import rocks.cleanstone.net.minecraft.packet.enums.DisplayedSkinPart;
import rocks.cleanstone.net.minecraft.packet.enums.PlayerAbility;
import rocks.cleanstone.player.event.MoveReason;
import rocks.cleanstone.player.event.PlayerTeleportEvent;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;

public abstract class AbstractPlayer implements Player {

    protected final Identity id;
    protected Human entity;
    private boolean op = false, flying = false;
    private GameMode gameMode = VanillaGameMode.CREATIVE;
    private float flyingSpeed = 0.1F;
    private int viewDistance = 4;
    private Locale locale = Locale.ENGLISH;
    private ChatMode chatMode = ChatMode.ENABLED;
    private MainHandSide mainHandSide = MainHandSide.RIGHT;
    private Collection<DisplayedSkinPart> displayedSkinParts = new HashSet<>();

    public AbstractPlayer(Identity id) {
        this.id = id;
    }

    @Override
    public Identity getID() {
        return id;
    }

    @Override
    public String getFormattedName() {
        return id.getName();
    }

    @Override
    public boolean isOp() {
        return op;
    }

    @Override
    public void setOp(boolean op) {
        this.op = op;
    }

    @Nullable
    @Override
    public Human getEntity() {
        return entity;
    }

    @Override
    public void setEntity(Human entity) {
        Preconditions.checkNotNull(entity, "Player entity cannot be null after player init");
        this.entity = entity;
    }

    @Override
    public GameMode getGameMode() {
        return gameMode;
    }

    @Override
    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    @Override
    public float getFlyingSpeed() {
        return flyingSpeed;
    }

    public void setFlyingSpeed(float flyingSpeed) {
        this.flyingSpeed = flyingSpeed;
    }

    @Override
    public boolean isFlying() {
        return flying;
    }

    @Override
    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    @Override
    public Collection<PlayerAbility> getAbilities() {
        Collection<PlayerAbility> abilities = gameMode.getPlayerAbilities();
        if (isFlying()) abilities.add(PlayerAbility.IS_FLYING);
        return abilities;
    }

    @Override
    public void teleport(RotatablePosition newPosition, MoveReason moveReason) {
        HeadRotatablePosition oldPosition = getEntity().getPosition();
        HeadRotatablePosition newHeadRotatablePosition = new HeadRotatablePosition(newPosition, oldPosition.getHeadRotation());

        CleanstoneServer.publishEvent(new PlayerTeleportEvent(this, oldPosition, newHeadRotatablePosition, moveReason));
    }

    public int getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public ChatMode getChatMode() {
        return chatMode;
    }

    public void setChatMode(ChatMode chatMode) {
        this.chatMode = chatMode;
    }

    public MainHandSide getMainHandSide() {
        return mainHandSide;
    }

    public void setMainHandSide(MainHandSide mainHandSide) {
        this.mainHandSide = mainHandSide;
    }

    public Collection<DisplayedSkinPart> getDisplayedSkinParts() {
        return displayedSkinParts;
    }

    public void setDisplayedSkinParts(Collection<DisplayedSkinPart> displayedSkinParts) {
        this.displayedSkinParts = displayedSkinParts;
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPlayer)) return false;
        AbstractPlayer that = (AbstractPlayer) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

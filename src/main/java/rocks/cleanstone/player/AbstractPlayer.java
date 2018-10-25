package rocks.cleanstone.player;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import lombok.Data;
import rocks.cleanstone.core.CleanstoneServer;
import rocks.cleanstone.game.Identity;
import rocks.cleanstone.game.chat.ChatMode;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.entity.vanilla.Human;
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

@Data
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
    public String getName() {
        return id.getName();
    }

    @Override
    public void setEntity(Human entity) {
        Preconditions.checkNotNull(entity, "Player entity cannot be null after player init");
        this.entity = entity;
    }

    @Override
    public Collection<PlayerAbility> getAbilities() {
        final Collection<PlayerAbility> abilities = gameMode.getPlayerAbilities();
        if (isFlying()) abilities.add(PlayerAbility.IS_FLYING);
        return abilities;
    }

    @Override
    public void teleport(RotatablePosition newPosition, MoveReason moveReason) {
        final HeadRotatablePosition oldPosition = getEntity().getPosition();
        final HeadRotatablePosition newHeadRotatablePosition = new HeadRotatablePosition(newPosition, oldPosition.getHeadRotation());

        CleanstoneServer.publishEvent(new PlayerTeleportEvent(this, oldPosition, newHeadRotatablePosition, moveReason));
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractPlayer)) return false;
        final AbstractPlayer that = (AbstractPlayer) o;
        return Objects.equal(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

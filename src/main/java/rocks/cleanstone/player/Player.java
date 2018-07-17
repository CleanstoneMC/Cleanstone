package rocks.cleanstone.player;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Locale;
import java.util.concurrent.Future;

import rocks.cleanstone.game.chat.ChatMode;
import rocks.cleanstone.game.chat.message.Text;
import rocks.cleanstone.game.command.CommandSender;
import rocks.cleanstone.game.entity.RotatablePosition;
import rocks.cleanstone.game.entity.vanilla.Human;
import rocks.cleanstone.game.gamemode.GameMode;
import rocks.cleanstone.game.inventory.MainHandSide;
import rocks.cleanstone.net.packet.Packet;
import rocks.cleanstone.net.packet.enums.DisplayedSkinPart;
import rocks.cleanstone.net.packet.enums.PlayerAbility;
import rocks.cleanstone.player.event.PlayerMoveEvent;

public interface Player extends CommandSender {

    void sendPacket(Packet packet);

    InetAddress getAddress();

    int getPing();

    Future<Void> kick(Text reason);

    Human getEntity();

    void setEntity(Human entity);

    GameMode getGameMode();

    void setGameMode(GameMode gameMode);

    boolean isOp();

    void setOp(boolean state);

    Collection<UserProperty> getUserProperties();

    boolean isFlying();

    void setFlying(boolean flying);

    float getFlyingSpeed();

    void setFlyingSpeed(float flyingSpeed);

    int getViewDistance();

    void setViewDistance(int viewDistance);

    Locale getLocale();

    void setLocale(Locale locale);

    ChatMode getChatMode();

    void setChatMode(ChatMode chatMode);

    MainHandSide getMainHandSide();

    void setMainHandSide(MainHandSide mainHandSide);

    Collection<DisplayedSkinPart> getDisplayedSkinParts();

    void setDisplayedSkinParts(Collection<DisplayedSkinPart> displayedSkinParts);

    Collection<PlayerAbility> getAbilities();

    void teleport(RotatablePosition position, PlayerMoveEvent.MoveReason moveReason);
}

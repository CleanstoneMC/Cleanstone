package rocks.cleanstone.player.event;

import rocks.cleanstone.game.entity.EntityMoveEvent;
import rocks.cleanstone.game.entity.HeadRotatablePosition;
import rocks.cleanstone.player.Player;

public class PlayerMoveEvent extends EntityMoveEvent {
    private final Player player;
    private final MoveReason moveReason;

    public PlayerMoveEvent(Player player, HeadRotatablePosition oldPosition,
                           HeadRotatablePosition newPosition, MoveReason moveReason) {
        super(player.getEntity(), oldPosition, newPosition);
        this.player = player;
        this.moveReason = moveReason;
    }

    public Player getPlayer() {
        return player;
    }

    public MoveReason getMoveReason() {
        return moveReason;
    }

    public enum StandardMoveReason implements MoveReason {
        /**
         * Client sent move packet
         */
        CLIENT_ACTION(0),
        /**
         * Client triggered actions (e.g. commands, enderpearls, entering minigame arena)
         */
        CLIENT_REQUEST(1),
        /**
         * Player moved by server event (e.g. respawn, teleporting out of protected area)
         */
        SERVER_EVENT(2),
        /**
         * Player moved by Cleanstone (e.g. physics, postponed movement corrections)
         */
        SERVER_INTERNAL(3);

        private final int typeID;

        StandardMoveReason(int typeID) {
            this.typeID = typeID;
        }

        @Override
        public int getTypeID() {
            return typeID;
        }
    }

    public interface MoveReason {
        int getTypeID();
    }
}

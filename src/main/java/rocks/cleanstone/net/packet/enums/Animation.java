package rocks.cleanstone.net.packet.enums;

public enum Animation {
    SWING_MAIN_ARM(0),
    TAKE_DAMAGE(1),
    LEAVE_BED(2),
    SWING_OFFHAND(3),
    CRITICAL_EFFECT(4),
    MAGIC_CRITICAL_EFFECT(5);

    private final int animationID;

    Animation(int animationID) {
        this.animationID = animationID;
    }

    @SuppressWarnings("Duplicates")
    public static Animation fromAnimationID(int animationID) {
        for (Animation animation : Animation.values()) {
            if (animation.getAnimationID() == animationID)
                return animation;
        }

        return null;
    }

    public int getAnimationID() {
        return animationID;
    }
}

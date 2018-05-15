package rocks.cleanstone.game.permission;

public abstract class AbstractPermission implements Permission {

    public AbstractPermission(PermissionsManager permissionsManager) {

    }

    @Override
    public boolean getDefault() {
        return false;
    }
}

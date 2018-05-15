package rocks.cleanstone.game.permission;

public class PermissionNode {

    private Permission permission;
    private PermissionScope scope;
    private PermissionExpiration expiration;

    public PermissionNode() {

    }

    public Permission getPermission() {
        return permission;
    }


}

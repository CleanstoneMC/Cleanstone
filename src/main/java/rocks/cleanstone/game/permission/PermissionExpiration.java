package rocks.cleanstone.game.permission;

public interface PermissionExpiration {
    boolean isExpired(Permission permission, Permissible permissible);
}

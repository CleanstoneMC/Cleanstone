package rocks.cleanstone.game.permission;

public interface PermissionScope {
    boolean permissionApplies(Permission permission, Permissible permissible);
    boolean canBeAppliedTo(Permissible permissible);
}

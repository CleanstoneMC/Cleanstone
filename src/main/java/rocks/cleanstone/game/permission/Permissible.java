package rocks.cleanstone.game.permission;

public interface Permissible {
    boolean checkPermission(Permission permission);

    boolean checkPermission(SecuredAction action);

    boolean hasPermission(Permission permission);

    boolean hasPermission(SecuredAction action);
}

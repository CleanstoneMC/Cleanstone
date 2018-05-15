package rocks.cleanstone.game.permission;

import java.util.Collection;

public interface PermissionsManager {
    
    void setPermission(Permissible permissible, Permission permission);

    void registerPermission(Permission permission);

    Collection<Permission> getAllPermissions();
}

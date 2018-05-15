package rocks.cleanstone.game.permission;

import java.util.Collection;

public interface PermissionsGroup {
    Collection<PermissionNode> getPermissionNodes();
    int getLevel();
}

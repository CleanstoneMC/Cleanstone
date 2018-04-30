package rocks.cleanstone.net.minecraft;

import rocks.cleanstone.net.Networking;
import rocks.cleanstone.net.minecraft.login.LoginManager;

public interface MinecraftNetworking extends Networking {
    LoginManager getLoginManager();
}

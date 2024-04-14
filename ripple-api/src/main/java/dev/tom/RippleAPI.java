package dev.tom;

import org.bukkit.plugin.java.JavaPlugin;

public final class RippleAPI {

    public static void initialize(JavaPlugin plugin){
        Ripple.initalize(plugin);
    }

    public static void newAnimation(){
        if(!Ripple.isInitialized()) {
            Ripple.logger.severe("Ripple has not been initialized!");
            Ripple.logger.severe("Please call RippleAPI#initialize(JavaPlugin) before creating animations!");
            return;
        }
    }
}

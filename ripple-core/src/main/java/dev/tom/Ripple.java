package dev.tom;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Ripple {

    @Getter
    private static JavaPlugin instance;
    public static Logger logger;

    public static void initalize(JavaPlugin plugin) {
        if(instance != null) {
            plugin.getLogger().severe("Ripple has already been initialized!");
            return;
        }
        instance = plugin;
        logger = instance.getLogger();
    }

    public static boolean isInitialized() {
        return instance != null;
    }

}
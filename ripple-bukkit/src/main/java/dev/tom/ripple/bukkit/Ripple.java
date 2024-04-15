package dev.tom.ripple.bukkit;

import dev.tom.ripple.core.RippleCore;
import org.bukkit.plugin.java.JavaPlugin;

public class Ripple {

    public static void initialize(JavaPlugin plugin){
        RippleCore.initialize(plugin);
    }

    public static void shutdown(){
        RippleCore.shutdown();
    }

    public static AnimationFactory getAnimationFactory(){
        if(!RippleCore.isInitialized()) {
            RippleCore.logger.severe("Ripple has not been initialized!");
            RippleCore.logger.severe("Please call RippleAPI#initialize(JavaPlugin) before creating animations!");
            throw new IllegalStateException("Ripple has not been initialized!");
        }
        return new AnimationFactory();
    }
}
package dev.tom.ripple.core;

import dev.tom.ripple.core.animation.AbstractAnimation;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class RippleCore {

    private static @Getter Set<AbstractAnimation> animations = new HashSet<>();

    @Getter
    private static JavaPlugin instance;
    public static Logger logger;

    public static void initialize(JavaPlugin plugin) {
        if(instance != null) {
            plugin.getLogger().severe("Ripple has already been initialized!");
            return;
        }
        instance = plugin;
        logger = instance.getLogger();
    }

    public static void shutdown() {
        if(instance == null) {
            logger.severe("Ripple has not been initialized, cannot shutdown!");
            return;
        }
        for (AbstractAnimation animation : animations) {

        }
    }

    public static boolean isInitialized() {
        return instance != null;
    }



}
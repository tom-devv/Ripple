package dev.tom.rippleexample;

import dev.tom.RippleAPI;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        RippleAPI.initialize(this);
        RippleAPI.newAnimation();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

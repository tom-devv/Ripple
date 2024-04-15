package dev.tom.ripple.example;

import dev.tom.ripple.api.event.AnimationStartEvent;
import dev.tom.ripple.bukkit.AnimationFactory;
import dev.tom.ripple.bukkit.Ripple;
import dev.tom.ripple.core.RippleCore;
import dev.tom.ripple.core.animation.AbstractAnimation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Ripple.initialize(this);
        this.getCommand("example").setExecutor(new ExampleCommand());
    }

    @EventHandler
    public void a(AnimationStartEvent e){

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

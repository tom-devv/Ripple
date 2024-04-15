package dev.tom.ripple.core.animation;

import dev.tom.ripple.api.animation.Repeatable;
import dev.tom.ripple.core.RippleCore;
import dev.tom.ripple.core.utils.BlockUtils;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Set;


public class SpinAnimation extends AbstractAnimation implements Repeatable {

    private boolean repeat = false;

    public SpinAnimation(long duration, double speed, World world, Set<Location> locations) {
        super(duration, speed, world,  locations);
    }


    @Override
    public void play() {
        startInternal();
        Set<Entity> entities = BlockUtils.locationToEntity(world, locations);
        for (Entity entity : entities) {
            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {

                }
            }.runTaskTimer(RippleCore.getInstance(), 0, 1);
        }
    }

    @Override
    public void end() {
        endInternal();
    }

    @Override
    public boolean isRepeat() {
        return this.repeat;
    }

    @Override
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }
}

package dev.tom.ripple.core.animation;

import dev.tom.ripple.api.animation.Repeatable;
import dev.tom.ripple.core.RippleCore;
import dev.tom.ripple.core.utils.BlockUtils;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.Set;


public class SpinAnimation extends AbstractAnimation implements Repeatable {

    private boolean repeat = false;
    private @Setter boolean  pitch = false;
    private @Setter boolean yaw = true;

    public SpinAnimation(long duration, double speed, World world, Set<Location> locations, boolean pitch, boolean yaw) {
        super(duration, speed, world,  locations);
        this.pitch = pitch;
        this.yaw = yaw;
    }

    @Override
    public void play() {
        Set<Entity> entities = BlockUtils.locationToEntity(world, locations);
        startInternal();
        for (Entity entity : entities) {
            BukkitTask task = new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    if (count > getDuration() && !isRepeat()) {
                        Location location = entity.getLocation();
                        location.setYaw(0);
                        location.setPitch(0);
                        location.setDirection(new Vector(0, 0, 1.0));
                        entity.teleport(location);
                        System.out.println("Teleporting");
                        endInternal();
                        end();
                        this.cancel();
                    }
                    if(yaw){
                        entity.teleport(entity.getLocation().setDirection(entity.getLocation().getDirection().rotateAroundY(Math.toRadians(getSpeed()))));
                    }
                    if(pitch){
                        entity.teleport(entity.getLocation().setDirection(entity.getLocation().getDirection().rotateAroundX(Math.toRadians(getSpeed()))));
                    }
                    count++;
                }
            }.runTaskTimer(RippleCore.getInstance(), 0, 1);
            setAnimationTask(task);
        }
    }

    @Override
    public void end() {

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

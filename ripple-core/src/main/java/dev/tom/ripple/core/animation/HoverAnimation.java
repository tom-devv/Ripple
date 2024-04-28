package dev.tom.ripple.core.animation;

import dev.tom.ripple.api.animation.Repeatable;
import dev.tom.ripple.core.RippleCore;
import dev.tom.ripple.core.utils.BlockUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.util.*;

public class HoverAnimation extends AbstractAnimation implements Repeatable {

    private double translation = 0;
    private boolean repeat = false;

    public HoverAnimation(long duration, double speed, World world, Set<Location> locations) {
        super(duration, speed, world, locations);
    }

    @Override
    public void play() {
        setEntities(BlockUtils.locationToEntity(world, locations));
        startInternal();
        BukkitTask task = new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                if (i > getDuration()) {
                    endInternal();
                    end();
                    this.cancel();
                }
                double trans = Math.sin((double) i / 100) * getSpeed();
                for (Entity entity : entities) {
                    entity.teleport(entity.getLocation().add(0, trans, 0));
                }
                translation = translation + trans;
                System.out.println(translation);
                i++;
            }
        }.runTaskTimer(RippleCore.getInstance(), 0, 1);
        setAnimationTask(task);
    }

    // Set the block data back to the original state and remove the entities
    @Override
    public void end() {
        int translationVector = (int) Math.round(this.translation);
        for (Entity entity : entities) {
            entity.remove();
        }
        for (Location location : locations) {
            BlockData data = blocks.get(location);
            location.add(0, translationVector, 0).getBlock().setBlockData(data, false);
        }
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

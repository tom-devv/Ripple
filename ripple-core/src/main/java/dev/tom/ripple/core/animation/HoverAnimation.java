package dev.tom.ripple.core.animation;

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

public class HoverAnimation extends AbstractAnimation {

    private final Set<Location> locations;
    private Map<Location, BlockData> blockMap = new HashMap<>();
    private Set<Entity> entities = new HashSet<>();
    private final World world;
    private double translation = 0;

    public HoverAnimation(long duration, double speed, World world, Set<Location> locations) {
        super(duration, speed);
        this.locations = locations;
        this.world = world;
        for (Location location : locations) {
            blockMap.put(location.clone(), location.getBlock().getBlockData().clone());
        }
    }

    @Override
    public void play() {
        startInternal();
        entities = BlockUtils.locationToEntity(world, locations);
        for (Location location : locations) {
            location.getBlock().setType(Material.AIR, false);
        }
        BukkitTask task = new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if (i > getDuration()) {
                    endInternal();
                    end();
                    this.cancel();
                }
                double trans = Math.sin((double) i / 10) * 0.1;
                for (Entity entity : entities) {
                    entity.teleport(entity.getLocation().add(0, trans, 0));
                }
                translation = translation + trans;
                System.out.println(translation);
                i++;
            }
        }.runTaskTimer(RippleCore.getInstance(), 0, 1);
    }

    // Set the block data back to the original state and remove the entities
    @Override
    public void end() {
        int translationVector = (int) Math.round(this.translation);

        for (Entity entity : entities) {
            entity.remove();
        }
        for (Location location : locations) {
            BlockData data = blockMap.get(location);
            location.add(0, translationVector, 0).getBlock().setBlockData(data, false);
        }

    }
}

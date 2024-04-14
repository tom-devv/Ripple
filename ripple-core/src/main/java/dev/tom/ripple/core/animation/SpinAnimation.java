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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class SpinAnimation extends AbstractAnimation {

    private final World world;
    private final Location center;
    private final int radius;
    private final Set<Location> locations;
    private Set<Entity> entities = new HashSet<>();
    private Map<Location, BlockData> blockMap= new HashMap<>();
    private Set<Location> blockLocations = new HashSet<>();
    private Set<BukkitTask> blockTasks = new HashSet<>();

    public SpinAnimation(long duration, double speed, World world, Location center, int radius) {
        super(duration, speed);
        this.world = world;
        this.center = center;
        this.radius = radius;
        this.locations = BlockUtils.getNearbyLocations(center, radius);
        for (Location location : this.locations) {
            blockMap.put(location.clone(), location.getBlock().getBlockData().clone());
            blockLocations.add(location);
        }
    }

//    private Location  getOffsetToCircumfrence(Location location) {
//        Vector vector = location.toVector().subtract(center.toVector());
//        double angle = Math.toRadians(360 / locations.size());
//        double x = vector.getX() * Math.cos(angle) - vector.getZ() * Math.sin(angle);
//        double z = vector.getX() * Math.sin(angle) + vector.getZ() * Math.cos(angle);
//        return center.clone().add(x, 0, z);
//    }


    @Override
    public void play() {
        startInternal();
        entities = BlockUtils.locationToEntity(world, locations);
        for (Location location : blockLocations) {
            Block block = location.getBlock();
            block.setType(Material.AIR, false);
        }

        int point = entities.size();
        double increment = 2 * Math.PI / point;
        int i = 0;
        for (Entity entity : entities) {
            Location cent = center.clone();
            double angle = i * increment;
            cent.add(radius * Math.cos(angle), 0, radius * Math.sin(angle));
            Vector diff = cent.toVector().subtract(entity.getLocation().toVector());
            double steps = getSpeed();

            new BukkitRunnable(){
                private int count = 0;
                @Override
                public void run(){
                    if(count > getDuration()){
                        entity.teleport(cent);
                        endInternal();
                        end();
                        this.cancel();
                    }
                    double t = count / steps;
                    Vector current = entity.getLocation().toVector().add(diff.multiply(t));
                    entity.teleport(current.toLocation(world));
                    count++;
                }
            }.runTaskTimer(RippleCore.getInstance(), 0, 1);
            i++;
        }
    }

    @Override
    public void end() {
        for (Entity entity : entities) {
            entity.remove();
        }
        for (Location location : locations) {
            BlockData data = blockMap.get(location);
            location.getBlock().setBlockData(data, false);
        }
    }
}

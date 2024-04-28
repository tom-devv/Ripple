package dev.tom.ripple.core.utils;


import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class BlockUtils {
    
    public Set<BlockDisplay> blockToDisplay(World world, Set<Block> blocks) {
        Set<BlockDisplay> entities = new HashSet<>();
        for (Block block : blocks) {
            BlockDisplay blockDisplay = world.spawn(block.getLocation(), BlockDisplay.class, (display) -> {
                display.setInterpolationDelay(1);
                display.setInterpolationDuration(80);
                display.setBlock(block.getBlockData());
                display.setPersistent(false);
                display.setGravity(false);
                display.setTeleportDuration(1);
            });
            entities.add(blockDisplay);
        };
        return entities;
    }

    public Set<BlockDisplay> locationToEntity(World world, Set<Location> locations) {
        Set<Block> blocks = new HashSet<>();
        for (Location location : locations) {
            BlockData data = location.getBlock().getBlockData();
            if(data.getMaterial().equals(Material.AIR)) continue;
            blocks.add(location.getBlock());
        };
        return blockToDisplay(world, blocks);
    }

    public Set<Location> getNearbyLocations(Location location, double radius){
        Set<Location> locations = new HashSet<>();
        for (double x = -radius; x <= radius; x++) {
            for (double y = -radius; y <= radius; y++) {
                for (double z = -radius; z <= radius; z++) {
                    Location newLocation = location.clone().add(x, y, z);
                    locations.add(newLocation);
                }
            }
        }
        return locations;
    }
}

package dev.tom.ripple.core.animation;

import dev.tom.ripple.api.animation.Animation;
import dev.tom.ripple.api.event.AnimationEndEvent;
import dev.tom.ripple.api.event.AnimationResetEvent;
import dev.tom.ripple.api.event.AnimationStartEvent;
import dev.tom.ripple.core.RippleCore;
import dev.tom.ripple.core.animation.state.AnimationState;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public abstract class AbstractAnimation implements Animation {

    private AnimationState state = AnimationState.IDLE;

    protected final long duration;
    protected final double speed;
    protected final World world;
    protected final Set<Location> locations;
    protected final Map<Location, BlockData> blocks = new HashMap<>();
    protected @Setter Set<BlockDisplay> entities = new HashSet<>();
    protected @Setter BukkitTask animationTask = null;

    public AbstractAnimation(long duration, double speed, @NonNull  World world,  @NonNull Set<Location> locations) {
        this.duration = duration;
        this.speed = speed;
        this.world = world;
        this.locations = locations;
        for (Location location : this.locations) {
            blocks.put(location, location.getBlock().getBlockData());
        }
    }

    protected void startInternal() {
        state = AnimationState.RUNNING;
        for (Location location : locations) {
            location.getBlock().setType(Material.AIR, false);
        }
        RippleCore.getAnimations().add(this);
        Bukkit.getServer().getPluginManager().callEvent(new AnimationStartEvent(this));
    }

    protected void endInternal(){
        state = AnimationState.STOPPED;
        RippleCore.getAnimations().remove(this);
        Bukkit.getServer().getPluginManager().callEvent(new AnimationEndEvent(this));
    }

    @Override
    public void reset(Set<BlockDisplay> entities) {
        RippleCore.getAnimations().remove(this);
        Bukkit.getServer().getPluginManager().callEvent(new AnimationResetEvent(this));
        for (Map.Entry<Location, BlockData> locationBlockEntry : blocks.entrySet()) {
            locationBlockEntry.getKey().getBlock().setBlockData(locationBlockEntry.getValue(), false);
        }
        if(entities.isEmpty()) return;
        for (Entity entity : entities) {
            entity.remove();
        }
    }

    public Set<BlockDisplay> addEntity(BlockDisplay entity) {
        this.entities.add(entity);
        return this.entities;
    }

    public Set<BlockDisplay> removeEntity(BlockDisplay entity) {
        this.entities.remove(entity);
        return this.entities;
    }

}

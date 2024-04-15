package dev.tom.ripple.core.animation;

import dev.tom.ripple.api.animation.Animation;
import dev.tom.ripple.api.event.AnimationEndEvent;
import dev.tom.ripple.api.event.AnimationResetEvent;
import dev.tom.ripple.api.event.AnimationStartEvent;
import dev.tom.ripple.core.RippleCore;
import dev.tom.ripple.core.animation.state.AnimationState;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;

import java.util.HashMap;
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
        RippleCore.getAnimations().add(this);
        Bukkit.getServer().getPluginManager().callEvent(new AnimationStartEvent(this));
    }

    protected void endInternal(){
        state = AnimationState.STOPPED;
        RippleCore.getAnimations().remove(this);
        Bukkit.getServer().getPluginManager().callEvent(new AnimationEndEvent(this));
    }

    @Override
    public void reset(Set<Entity> entities) {
        RippleCore.getAnimations().remove(this);
        Bukkit.getServer().getPluginManager().callEvent(new AnimationResetEvent(this));
        for (Entity entity : entities) {
            entity.remove();
        }
        for (Map.Entry<Location, BlockData> locationBlockEntry : blocks.entrySet()) {
            locationBlockEntry.getKey().getBlock().setBlockData(locationBlockEntry.getValue(), false);
        }

    }
}

package dev.tom.ripple.bukkit;

import dev.tom.ripple.core.animation.AbstractAnimation;
import dev.tom.ripple.core.animation.ExplodeAnimation;
import dev.tom.ripple.core.animation.HoverAnimation;
import dev.tom.ripple.core.animation.SpinAnimation;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import java.util.Set;

public class AnimationFactory {

     AnimationFactory(){}

    public AbstractAnimation initializeHoverAnimation(long duration, double speed, World world, Set<Location> locations) {
        return new HoverAnimation(duration, speed, world, locations);
    }

    public AbstractAnimation initializeSpinAnimation(long duration, double speed, World world, Set<Location> locations) {
        return new SpinAnimation(duration, speed, world, locations, true, true);
    }

    public AbstractAnimation initializeExplodeAnimation(long duration, long expandTick, Particle particle, int particleNumber, World world, Set<Location> locations) {
        return new ExplodeAnimation(duration, expandTick, particle, particleNumber, world, locations);
    }
}

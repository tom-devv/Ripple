package dev.tom.ripple.bukkit;

import dev.tom.ripple.core.animation.AbstractAnimation;
import dev.tom.ripple.core.animation.HoverAnimation;
import dev.tom.ripple.core.animation.SpinAnimation;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Set;

public class AnimationFactory {

     AnimationFactory(){}

    public AbstractAnimation initializeHoverAnimation(long duration, double speed, World world, Set<Location> locations) {
        return new HoverAnimation(duration, speed, world, locations);
    }

    public AbstractAnimation initializeSpinAnimation(long duration, double speed, World world, Location center, int radius) {
        return new SpinAnimation(duration, speed, world, center, radius);
    }
}
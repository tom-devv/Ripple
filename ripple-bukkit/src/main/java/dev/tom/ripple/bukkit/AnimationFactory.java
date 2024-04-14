package dev.tom.ripple.bukkit;

import dev.tom.ripple.core.animation.AbstractAnimation;
import dev.tom.ripple.core.animation.HoverAnimation;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Set;

public class AnimationFactory {

     AnimationFactory(){}

    public AbstractAnimation initializeHoverAnimation(long duration, double speed, World world, Set<Location> locations) {
        return new HoverAnimation(duration, speed, world, locations);
    }
}

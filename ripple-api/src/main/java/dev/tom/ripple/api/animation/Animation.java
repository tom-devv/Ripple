package dev.tom.ripple.api.animation;

import org.bukkit.entity.Entity;

import java.util.Set;

public interface Animation {

    /**
     * Play the animation
     */
    void play();

    /**
     * * End the animation
     */
    void end();

    /**
     * Reset the animation to its initial state
     * @param entities the entities to remove
     */
    void reset(Set<Entity> entities);

}

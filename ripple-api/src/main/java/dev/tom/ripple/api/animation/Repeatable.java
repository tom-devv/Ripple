package dev.tom.ripple.api.animation;

/**
 * Represents an animation that can be repeated forever.
 */
public interface Repeatable {
    /**
     * Check if the animation is set to repeat. A repeated animation will repeat forever until
     * the animation is stopped or the server is stopped, this resets the animation to its original
     * state.
     * @return if the animation is set to repeat
     */
    boolean isRepeat();

    /**
     * Set the animation to repeat. A repeated animation will repeat forever until
     * @param repeat if the animation should repeat
     */
    void setRepeat(boolean repeat);
}

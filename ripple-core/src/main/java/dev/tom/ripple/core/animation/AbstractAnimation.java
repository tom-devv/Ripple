package dev.tom.ripple.core.animation;

import dev.tom.ripple.api.animation.Animation;
import dev.tom.ripple.api.event.AnimationEndEvent;
import dev.tom.ripple.api.event.AnimationStartEvent;
import dev.tom.ripple.core.animation.state.AnimationState;
import lombok.Getter;
import org.bukkit.Bukkit;

@Getter
public abstract class AbstractAnimation implements Animation {

    private final long duration;
    private AnimationState state = AnimationState.IDLE;
    private final double speed;

    public AbstractAnimation(long duration, double speed) {
        this.duration = duration;
        this.speed = speed;
    }

    protected void startInternal() {
        state = AnimationState.RUNNING;
        Bukkit.getServer().getPluginManager().callEvent(new AnimationStartEvent(this));
    }

    protected void endInternal(){
        state = AnimationState.STOPPED;
        Bukkit.getServer().getPluginManager().callEvent(new AnimationEndEvent(this));
    }

}

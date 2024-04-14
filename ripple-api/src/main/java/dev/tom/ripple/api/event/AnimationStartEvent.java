package dev.tom.ripple.api.event;

import dev.tom.ripple.api.animation.Animation;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.event.Cancellable;

public class AnimationStartEvent extends RippleEvent implements Cancellable {

    @NonNull @Getter
    private final Animation animation;
    private boolean cancelled = false;

    public AnimationStartEvent(@NonNull Animation animation) {
        this.animation = animation;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
            this.cancelled = b;
    }
}

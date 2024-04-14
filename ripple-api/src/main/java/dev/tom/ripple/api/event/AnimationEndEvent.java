package dev.tom.ripple.api.event;

import dev.tom.ripple.api.animation.Animation;
import lombok.Getter;
import lombok.NonNull;

public class AnimationEndEvent extends RippleEvent {

    @NonNull @Getter private final Animation animation;

    public AnimationEndEvent(@NonNull Animation animation) {
        this.animation = animation;
    }

}

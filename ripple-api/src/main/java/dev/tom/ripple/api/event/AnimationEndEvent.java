package dev.tom.ripple.api.event;

import dev.tom.ripple.api.animation.Animation;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class AnimationEndEvent extends RippleEvent {

    @NonNull
    private final Animation animation;

    public AnimationEndEvent(@NonNull Animation animation) {
        this.animation = animation;
    }

}

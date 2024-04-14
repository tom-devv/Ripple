package dev.tom.event;

import dev.tom.animator.Animation;
import lombok.Getter;
import lombok.NonNull;

public class AnimationEndEvent extends RippleEvent {

    @NonNull @Getter private final Animation animation;

    public AnimationEndEvent(@NonNull Animation animation) {
        this.animation = animation;
    }

}

package com.suicidebomber.engine;

import com.badlogic.gdx.utils.TimeUtils;
import com.suicidebomber.source.ImageManager;
import java.util.HashMap;

// Signal: animation_ended

public class AnimatedSprite extends Sprite {

    private HashMap<String, AnimationSprite> animations = new HashMap<>();
    private String currentAnimation = "";
    private boolean isPlaying = false;
    private long startTime = 0;
    private long timingWhile = 0;
    private long pauseWhile = 0;
    private boolean isPause = false;

    public void addAnimation(String name, AnimationSprite animation) {
        animations.put(name, animation);
        addChild(animation);
        animation.connect_signal("animation_ended", this, "animation_ended");
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("animation_ended")) {
            emit_signal("animation_ended");
        }
    }

    public void play(String animName) {
        if (animations.containsKey(animName)) {
            if (currentAnimation.equals(animName)) {
                if (isPause) {
                    resume();
                }
                if (!animations.get(currentAnimation).isRunning) {
                    startTime = TimeUtils.millis();
                    animations.get(currentAnimation).start();
                }
            } else {
                if (isPlaying) {
                    animations.get(currentAnimation).stop();
                }
                currentAnimation = animName;
                animations.get(animName).start();
                startTime = TimeUtils.millis();
            }
            isPlaying = true;
        }
    }

    public void pause() {
        if (isPlaying) {
            isPlaying = false;
            pauseWhile = timingWhile;
            isPause = true;
        }
    }

    public void resume() {
        if (isPause) {
            isPlaying = true;
            isPause = false;
            startTime = TimeUtils.millis() - pauseWhile;
        }
    }

    public void stop() {
        isPlaying = false;
    }

    public void renderImage() {
        if (!currentAnimation.equals("")) {
            if (isPlaying) {
                timingWhile = TimeUtils.timeSinceMillis(startTime);
            }
            ImageManager.instance().drawImage(
                    animations.get(currentAnimation).getSprite(timingWhile),
                    global_position);
        }
    }
}

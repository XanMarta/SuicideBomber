package com.suicidebomber.engine;

import com.badlogic.gdx.utils.TimeUtils;
import com.suicidebomber.game.GameElement;
import java.util.HashMap;


public class AnimatedSprite extends Sprite {

    public HashMap<String, AnimationSprite> animations = new HashMap<>();
    public String currentAnimation = "";
    public boolean isPlaying = false;

    private long startTime = 0;
    private long timingWhile = 0;
    private long pauseWhile = 0;
    private boolean isPause = false;

    public void addAnimation(String name, AnimationSprite animation) {
        animations.put(name, animation);
    }

    public void play(String name) {
        if (animations.containsKey(name)) {
            if (currentAnimation.equals(name)) {
                if (isPause) {
                    resume();
                }
            } else {
                currentAnimation = name;
                startTime = TimeUtils.millis();
            }
            isPlaying = true;
        }
    }

    public void start() {
        isPlaying = true;
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
        currentAnimation = "";
    }

    public void renderImage() {
        if (!currentAnimation.equals("")) {
            if (isPlaying) {
                timingWhile = TimeUtils.timeSinceMillis(startTime);
            }
            GameElement.imageManager.drawImage(
                    animations.get(currentAnimation).getSprite(timingWhile),
                    global_position);
        }
    }
}

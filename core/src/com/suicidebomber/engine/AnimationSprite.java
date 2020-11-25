package com.suicidebomber.engine;

import java.util.ArrayList;

// Signal: animation_ended

public class AnimationSprite extends Node {

    public long delay = 500;
    public boolean looping = true;
    public boolean isRunning = false;

    private ArrayList<String> sprites = new ArrayList<>();

    public void addSprite(String sprite) {
        sprites.add(sprite);
    }

    public void start() {
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }

    public String getSprite(long timeWhile) {
        if (sprites.size() == 0) {
            return "";
        }
        long frame = timeWhile / delay;
        if (looping) {
            return sprites.get((int) frame % sprites.size());
        } else {
            if (frame < sprites.size()) {
                return sprites.get((int) frame % sprites.size());
            } else {
                if (isRunning) {
                    isRunning = false;
                    emit_signal("animation_ended");
                }
                return sprites.get(sprites.size() - 1);
            }
        }
    }

}

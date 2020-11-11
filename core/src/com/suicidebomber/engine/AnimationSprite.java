package com.suicidebomber.engine;

import java.util.ArrayList;

public class AnimationSprite {

    public ArrayList<String> sprites = new ArrayList<>();
    public long delay = 500;

    public void addSprite(String sprite) {
        sprites.add(sprite);
    }

    public String getSprite(long timeWhile) {
        if (sprites.size() == 0) {
            return "";
        }
        return sprites.get((int) (timeWhile / delay) % sprites.size());
    }

}

package com.suicidebomber.source.manager;

import java.util.Random;


public class GameHelper {

    private static GameHelper gameHelper = null;

    public Random random;

    private GameHelper() {

    }

    public static GameHelper instance() {
        if (gameHelper == null) {
            gameHelper = new GameHelper();
        }
        return gameHelper;
    }

    public float clamp(float source, float min, float max) {
        if (source < min) {
            return min;
        } else if (source > max) {
            return max;
        }
        return source;
    }

    public void create() {
        random = new Random();
    }
}

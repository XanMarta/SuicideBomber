package com.suicidebomber.autoload;

import com.badlogic.gdx.math.Vector2;


public class GameElement {

    // Windows
    public static Vector2 windowsSize = new Vector2(1280, 960);

    // Block
    public static Vector2 blockSize = new Vector2(60, 60);
    public static Vector2 mapPosition = new Vector2(350, 10);
    public enum BlockType {
        WALL,
        GRASS,
        FIRE,
        BOX,
        BOMB,
        ITEM,
        NONE
    }

    // Actor
    public static float nearCenterMargin = 20;
    public static float centerMargin = 2;
    public static float defaultSpeed = 4;

    // Bomb
    public static long bombTiming = 3000;
    public static long fireTiming = 800;
    public static long delayBombSpreadTime = 50;

    // Player
    public static int init_bomb = 1;
    public static int init_heart = 2;
    public static int init_power = 2;
    public static int init_speed = 1;
    public static int max_element = 5;

    // Font
    public static String defaultFont = "COMIC";

    // Match
    public static int matchTime = 20;

}

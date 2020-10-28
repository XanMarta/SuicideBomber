package com.suicidebomber;

import com.badlogic.gdx.math.Vector2;


public class GameElement {

    // Windows
    public static Vector2 windowsSize = new Vector2(1280, 960);

    // Block
    public static Vector2 blockSize = new Vector2(60, 60);
    public static Vector2 mapSize = new Vector2(15, 15);
    public static Vector2 mapPosition = new Vector2(250, 20);
    public enum BlockType {
        WALL,
        GRASS,
        FIRE,
        BOMB
    }

    // Source Manager
    public static ImageManager imageManager = new ImageManager();

    // Actor
    public static float changeDirectionMargin = 20;
    public static float autoMargin = 2;
    public static float defaultSpeed = 5;

    // Bomb
    public static float bombTiming = 3.0f;

}

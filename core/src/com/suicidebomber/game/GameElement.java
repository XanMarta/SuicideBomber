package com.suicidebomber.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.suicidebomber.engine.Node;
import com.suicidebomber.source.ImageManager;
import com.suicidebomber.source.MapLoader;


public class GameElement {

    // Windows
    public static Vector2 windowsSize = new Vector2(1280, 960);

    // Block
    public static Vector2 blockSize = new Vector2(60, 60);
    public static Vector2 mapPosition = new Vector2(250, 20);
    public enum BlockType {
        WALL,
        GRASS,
        FIRE,
        BOX,
        BOMB,
        ITEM,
        NONE
    }

    // Source Manager
    public static ImageManager imageManager = new ImageManager();
    public static MapLoader mapLoader = new MapLoader();

    // Actor
    public static float changeDirectionMargin = 20;
    public static float autoMargin = 2;
    public static float defaultSpeed = 5;

    // Bomb
    public static float bombTiming = 3.0f;
    public static float fireTiming = 0.8f;
    public static float delayBombSpreadTime = 0.05f;

    // Player
    public static int init_bomb = 2;
    public static int init_heart = 2;
    public static int init_power = 3;
    public static int init_speed = 2;
    public static int max_element = 5;

}

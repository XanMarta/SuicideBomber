package com.suicidebomber.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.source.FontManager;
import com.suicidebomber.source.ImageManager;
import com.suicidebomber.source.MapLoader;
import com.suicidebomber.source.SoundManager;


public class GameElement {

    // Windows
    public static Vector2 windowsSize = new Vector2(1280, 960);

    // Block
    public static Vector2 blockSize = new Vector2(60, 60);
    public static Vector2 centerOffset = new Vector2(30, 30);
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

    // Source Manager
    public static SpriteBatch batch;
    public static ImageManager imageManager = new ImageManager();
    public static MapLoader mapLoader = new MapLoader();
    public static FontManager fontManager = new FontManager();
    public static SoundManager soundManager = new SoundManager();

    // Actor
    public static float changeDirectionMargin = 20;
    public static float autoMargin = 2;
    public static float defaultSpeed = 4;

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

    // Font
    public static String defaultFont = "COMIC";
}

package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.structure.GameElement;


public class MapScore {

    public int width;
    public int height;
    public float[][] score;

    public void generateMap(int width, int height) {
        this.width = width;
        this.height = height;
        score = new float[width][height];
        reset();
    }

    public void reset() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                score[i][j] = 0.0f;
            }
        }
    }

    public void addScore(Vector2 pos, float sc) {
        float current = sc + score[(int) pos.x][(int) pos.y];
        score[(int) pos.x][(int) pos.y] = GameElement.clamp(current, -1.0f, 1.0f);
    }

    public void copy(MapScore map) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.score[i][j] = map.score[i][j];
            }
        }
    }
}

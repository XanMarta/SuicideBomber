package com.suicidebomber.element;

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
}

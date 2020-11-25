package com.suicidebomber.source.sourceelement;

import com.suicidebomber.autoload.GameElement;


public class Map {
    public int width;
    public int height;
    public GameElement.BlockType[][] element;

    public void setMap(int width, int length) {
        this.width = width;
        this.height = length;
        element = new GameElement.BlockType[width][length];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                element[i][j] = GameElement.BlockType.GRASS;
            }
        }
    }

    public void setBlock(int x, int y, String type) { // From code
        switch (type) {
            case "W":
                element[x][y] = GameElement.BlockType.WALL;
                break;
            case "B":
                element[x][y] = GameElement.BlockType.BOX;
                break;
        }
    }
}

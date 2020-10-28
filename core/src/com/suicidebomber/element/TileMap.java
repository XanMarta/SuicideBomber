package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.GameElement;


public class TileMap extends Node2D {

    public Vector2 blockSize = new Vector2(10, 10);
    public Vector2 mapSize = new Vector2(0, 0);
    public MapBlock[][] mapBlock;

    public void generateMap(GameElement.BlockType[][] map, int x, int y) {
        this.mapSize.set(x, y);
        mapBlock = new MapBlock[x][y];
        for (int j = y - 1; j >= 0; j--) {
            for (int i = x - 1; i >= 0; i--) {
                mapBlock[i][j] = new MapBlock();
                mapBlock[i][j].setBlockType(map[i][j]);
                mapBlock[i][j].position.set(
                        i * blockSize.x + global_position.x,
                        j * blockSize.y + global_position.y);
                addChild(mapBlock[i][j]);
            }
        }
    }

    public Vector2 blockToPosition(Vector2 block) {
        return new Vector2(
                block.x * blockSize.x,
                block.y * blockSize.y);
    }

    public Vector2 positionToBlock(Vector2 pos) {
        Vector2 block = new Vector2(pos);
        block.set(block.x / blockSize.x, block.y / blockSize.y);
        return block.set((int) block.x, (int) block.y);
    }

    public MapBlock blockAt(Vector2 block) {
        if (isInMap(block)) {
            return mapBlock[(int) block.x][(int) block.y];
        } else {
            return null;
        }
    }

    public boolean isInMap(Vector2 block) {
        if (block.x >= 0 && block.x < mapSize.x && block.y >= 0 && block.y < mapSize.y) {
            return true;
        }
        return false;
    }

    public MapBlock getMapBlock(float x, float y) {
        return mapBlock[(int) x][(int) y];
    }

    public void addSprite(Sprite sprite, Vector2 block) {
        if (isInMap(block)) {
            getMapBlock(block.x, block.y).element.add(sprite);
        }
    }

}

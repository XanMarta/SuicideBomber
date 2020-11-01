package com.suicidebomber.engine;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.game.GameElement;
import com.suicidebomber.source.Map;


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

    public void generateMap(Map map) {
        mapSize.set(map.width, map.height);
        for (int j = map.height - 1; j >= 0; j--) {
            for (int i = map.width - 1; i >= 0; i--) {
                mapBlock[i][j] = new MapBlock();
                mapBlock[i][j].setBlockType(map.element[i][j]);
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

    public MapBlock getMapBlock(Vector2 block) {
        return mapBlock[(int) block.x][(int) block.y];
    }

    public void addElement(MapElement element, Vector2 block) {
        if (isInMap(block)) {
            getMapBlock(block).elements.add(element);
        }
    }

}

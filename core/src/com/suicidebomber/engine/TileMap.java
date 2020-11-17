package com.suicidebomber.engine;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.source.sourceelement.Map;

import java.util.ArrayList;


public class TileMap extends Node2D {

    public Vector2 blockSize = new Vector2(60, 60);
    public Vector2 centerOffset = new Vector2(30, 30);
    public Vector2 mapSize = new Vector2(0, 0);
    public MapBlock[][] mapBlock;
    public MapRow[] mapRows;
    public Sprite ground;

    public void generateMap(Map map) {
        // Ground
        ground = new Sprite();
        ground.image = "GROUND15";
        addChild(ground);
        // Map
        mapSize.set(map.width, map.height);
        mapBlock = new MapBlock[map.width][map.height];
        mapRows = new MapRow[map.height];
        for (int j = 0; j < map.height; j++) {
            mapRows[j] = new MapRow();
        }
        for (int j = map.height - 1; j >= 0; j--) {
            for (int i = map.width - 1; i >= 0; i--) {
                mapBlock[i][j] = new MapBlock();
                mapBlock[i][j].blockType = map.element[i][j];
                mapBlock[i][j].position.set(i, j).scl(blockSize).add(global_position);
                addChild(mapBlock[i][j]);
            }
            addChild(mapRows[j]);
        }
    }

    public Vector2 blockToCenter(Vector2 block) {
        return new Vector2(block).scl(blockSize).add(centerOffset);
    }

    public Vector2 blockToPosition(Vector2 block) {
        return new Vector2(block).scl(blockSize);
    }

    public Vector2 positionToBlock(Vector2 pos) {           // position of map
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
        return (block.x >= 0 && block.x < mapSize.x && block.y >= 0 && block.y < mapSize.y);
    }

    public MapBlock getMapBlock(Vector2 block) {
        return mapBlock[(int) block.x][(int) block.y];
    }

    public void addElement(MapElement element) {
        mapRows[(int) element.currentBlock.y].elements.add(element);
        mapBlock[(int) element.currentBlock.x][(int) element.currentBlock.y].newelements.add(element);
    }

}



class MapRow extends Node2D {

    public ArrayList<MapElement> elements = new ArrayList<>();

    public void render() {
        super.render();
        for (MapElement element : elements) {
            element.renderImage();
        }
        elements.clear();
    }
}

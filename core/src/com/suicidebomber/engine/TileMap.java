package com.suicidebomber.engine;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.autoload.GameElement;
import com.suicidebomber.element.MapScore;
import com.suicidebomber.source.Map;
import java.util.ArrayList;


public class TileMap extends Canvas2D {

    public Vector2 blockSize = new Vector2(60, 60);
    public Vector2 centerOffset = new Vector2(30, 30);
    public Vector2 mapSize = new Vector2(0, 0);

    private MapBlock[][] mapBlock;
    private MapRow[] mapRows;
    private Sprite ground;
    private MapScore mapScore = new MapScore();                  // Score to write
    private MapScore updateMapScore = new MapScore();            // Score to read
    private ArrayList<MapElement> elementScore = new ArrayList<>();

    public void render() {
        super.render();
        generateMapScore();
    }

    public void generateMap(Map map) {
        // Ground
        ground = new Sprite();
        ground.image = "GROUND15";
        addChild(ground);
        // Map
        mapSize.set(map.width, map.height);
        mapBlock = new MapBlock[map.width][map.height];
        mapScore.generateMap(map.width, map.height);
        updateMapScore.generateMap(map.width, map.height);
        mapRows = new MapRow[map.height];
        for (int j = 0; j < map.height; j++) {
            mapRows[j] = new MapRow();
        }
        for (int j = map.height - 1; j >= 0; j--) {
            for (int i = map.width - 1; i >= 0; i--) {
                mapBlock[i][j] = new MapBlock();
                mapBlock[i][j].blockType = map.element[i][j];
                mapBlock[i][j].currentBlock.set(i, j);
                mapBlock[i][j].position.set(i, j).scl(blockSize).add(global_position);
                addChild(mapBlock[i][j]);
            }
            addChild(mapRows[j]);
        }
    }

    public ArrayList<Vector2> getMapBlockList(GameElement.BlockType blockType) {
        ArrayList<Vector2> list = new ArrayList<>();
        for (int i = 0; i < mapSize.x; i++) {
            for (int j = 0; j < mapSize.y; j++) {
                if (mapBlock[i][j].blockType == blockType) {
                    list.add(mapBlock[i][j].currentBlock);
                }
            }
        }
        return list;
    }

    public void generateMapScore() {
        for (MapElement element : elementScore) {
            element.specialScore();
        }
        elementScore.clear();
        printMapScore();
        updateMapScore.copy(mapScore);
        mapScore.reset();
    }

    private void printMapScore() {
        for (int j = (int) mapSize.y - 1; j >= 0; j--) {
            for (int i = 0; i < mapSize.x; i++) {
                if (mapScore.score[i][j] >= 0.0) {
                    System.out.print(" ");
                }
                System.out.print(mapScore.score[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public float getScore(Vector2 block) {
        return updateMapScore.score[(int) block.x][(int) block.y];
    }

    public void setScore(Vector2 block, float score) {
        mapScore.addScore(block, score);
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
        MapBlock block = mapBlock[(int) element.currentBlock.x][(int) element.currentBlock.y];
        block.addElement(element);
        mapScore.addScore(element.currentBlock, element.initScore);
        elementScore.add(element);
    }
}


class MapRow extends Node2D {

    protected ArrayList<MapElement> elements = new ArrayList<>();

    public void render() {
        super.render();
        for (MapElement element : elements) {
            element.renderImage();
        }
        elements.clear();
    }
}

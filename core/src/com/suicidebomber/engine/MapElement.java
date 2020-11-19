package com.suicidebomber.engine;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.structure.GameElement;
import java.util.ArrayList;


public class MapElement extends Canvas2D {

    public TileMap currentMap = null;
    public GameElement.BlockType blockType = GameElement.BlockType.NONE;
    public Vector2 currentBlock = new Vector2(0, 0);
    public ArrayList<Sprite> renderElement = new ArrayList<>();             // Render to map
    public boolean elementVisible = true;
    public boolean isNearCenter = false;

    public void create() {
        super.create();
        visible = false;
    }

    public void setMap(TileMap map) {
        currentMap = map;
    }

    public void setBlock(Vector2 block) {
        currentBlock.set(block);
        position.set(currentMap.blockToPosition(block));
        if (blockType != GameElement.BlockType.NONE) {
            currentMap.getMapBlock(currentBlock).blockType = this.blockType;
        }
    }

    public void render() {
        super.render();
        if (currentMap.isInMap(currentBlock)) {
            currentMap.addElement(this);
        }
    }

    public void dispose() {
        super.dispose();
        if (currentMap.getMapBlock(currentBlock).blockType == blockType) {
            currentMap.getMapBlock(currentBlock).blockType = GameElement.BlockType.GRASS;
        }
    }

    public void appear() {

    }

    public void disappear() {

    }

    public void renderImage() {
        if (elementVisible) {
            for (Sprite sprite : renderElement) {
                sprite.renderImage();
            }
        }
    }
}

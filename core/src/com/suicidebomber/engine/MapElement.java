package com.suicidebomber.engine;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.autoload.GameElement;
import java.util.ArrayList;


public abstract class MapElement extends Canvas2D {

    protected TileMap currentMap = null;
    protected GameElement.BlockType blockType = GameElement.BlockType.NONE;
    protected ArrayList<Sprite> renderElement = new ArrayList<>();             // Render to map

    public Vector2 currentBlock = new Vector2(0, 0);
    public boolean elementVisible = true;
    public float initScore = 0.0f;

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

    // # Override method

    public void specialScore() {

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

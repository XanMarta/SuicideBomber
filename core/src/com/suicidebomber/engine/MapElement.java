package com.suicidebomber.engine;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.game.GameElement;


public class MapElement extends Canvas2D {

    public Sprite sprite;
    public TileMap currentMap = null;
    public GameElement.BlockType blockType = GameElement.BlockType.NONE;
    public Vector2 currentBlock = new Vector2(0, 0);
    public Vector2 nearbyBlock = new Vector2(-1, -1);
    public Vector2 center = new Vector2(0, 0);
    public boolean isElementShowing = true;

    public MapElement() {
        super();
        sprite = new Sprite();
    }

    public void create() {
        super.create();
        sprite.showing = false;
        addChild(sprite);
    }

    public void setMap(TileMap map) {
        currentMap = map;
    }

    public void setBlock(Vector2 block) {
        currentBlock.set(block);
        center.set(currentMap.blockToCenter(block));
        updatePosition();
        if (blockType != GameElement.BlockType.NONE) {
            currentMap.getMapBlock(currentBlock).blockType = this.blockType;
        }
    }

    public void updatePosition() {                              // Need calling after center change
        position.set(center).sub(currentMap.centerOffset);
        currentBlock.set(currentMap.positionToBlock(center));
        Vector2 exactCen = currentMap.blockToCenter(currentBlock);
        if (exactCen.epsilonEquals(center, 0.1f)) {
            nearbyBlock.set(-1, -1);
        } else {
            nearbyBlock.set(new Vector2(center).sub(exactCen).nor().add(currentBlock));
        }
    }

    public void render() {
        super.render();
        if (isElementShowing) {
            if (currentMap.isInMap(currentBlock)) {
                currentMap.addElement(this);
            }
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
}

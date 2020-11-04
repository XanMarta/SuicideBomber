package com.suicidebomber.engine;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.game.GameElement;


public class MapElement extends Node2D {

    public Sprite sprite;
    public TileMap currentMap = null;
    public GameElement.BlockType blockType = GameElement.BlockType.NONE;
    public Vector2 currentBlock = new Vector2(0, 0);

    public MapElement() {
        super();
        sprite = new Sprite();
        sprite.showing = false;
        addChild(sprite);
    }

    public void setMap(TileMap map, Vector2 block) {
        currentMap = map;
        currentBlock.set(block);
        position.set(currentMap.blockToPosition(block));
        if (blockType != GameElement.BlockType.NONE) {
            currentMap.getMapBlock(currentBlock).blockType = this.blockType;
        }
    }

    public void render() {
        super.render();
        if (currentMap.isInMap(currentBlock)) {
            currentMap.getMapBlock(currentBlock).elements.add(this);
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

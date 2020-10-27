package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;


public class MapElement extends Node2D {

    public Sprite sprite;
    public Vector2 currentBlock = new Vector2(0, 0);
    public TileMap currentMap = null;

    public MapElement() {
        sprite = new Sprite();
        sprite.showing = false;
        addChild(sprite);
    }

    public void setBlock(int x, int y) {
        position.set(currentMap.blockToPosition(new Vector2(x, y)));
        currentBlock.set(x, y);
    }

    public void setCurrentMap(TileMap currentMap) {
        this.currentMap = currentMap;
        position = currentMap.position;
    }

    public void updateBlock() {
        currentBlock.set(currentMap.positionToBlock(position));
    }

    public void render() {
        super.render();
        currentMap.addSprite(sprite, currentBlock);
    }
}

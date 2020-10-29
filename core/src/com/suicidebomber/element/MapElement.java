package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;


public class MapElement extends Node2D {

    public Sprite sprite;
    public Vector2 currentBlock = new Vector2(0, 0);
    public TileMap currentMap = null;

    public MapElement() {
        super();
        sprite = new Sprite();
        sprite.showing = false;
        addChild(sprite);
    }

    public void setBlock(int x, int y) {
        position.set(currentMap.blockToPosition(new Vector2(x, y)));
        currentBlock.set(x, y);
    }

    public void setBlock(Vector2 block) {
        setBlock((int) block.x, (int) block.y);
    }

    public void updateBlock() {
        currentBlock.set(currentMap.positionToBlock(position));
    }

    public void render() {
        super.render();
        currentMap.addElement(this, currentBlock);
    }
}

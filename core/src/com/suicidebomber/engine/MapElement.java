package com.suicidebomber.engine;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.game.GameElement;


public class MapElement extends Node2D {

    public Sprite sprite;
    public Vector2 currentBlock = new Vector2(0, 0);
    public TileMap currentMap = null;
    public GameElement.BlockType blockType = GameElement.BlockType.NONE;

    public MapElement() {
        super();
        sprite = new Sprite();
        sprite.showing = false;
        addChild(sprite);
    }

    public void setTileMap(TileMap map, int x, int y) {
        this.currentMap = map;
        setBlock(x, y);
    }

    public void setBlock(Vector2 block) {
        setBlock((int) block.x, (int) block.y);
    }

    public void setBlock(int x, int y) {
        position.set(currentMap.blockToPosition(new Vector2(x, y)));
        currentBlock.set(x, y);
        if (blockType != GameElement.BlockType.NONE) {
            currentMap.getMapBlock(currentBlock).setBlockType(blockType);
        }
    }

    public void updateBlock() {
        currentBlock.set(currentMap.positionToBlock(position));
    }

    public void render() {
        super.render();
        currentMap.addElement(this, currentBlock);
    }
}

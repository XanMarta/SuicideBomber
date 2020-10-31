package com.suicidebomber.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.game.GameElement;


public class Player extends Actor {

    public void render() {
        super.render();
        playerMovement();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            dropBomb();
        }
    }

    public void playerMovement() {
        Vector2 direction = new Vector2().setZero();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction.x = -1;
            direction.y = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction.x = 1;
            direction.y = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction.y = 1;
            direction.x = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction.y = -1;
            direction.x = 0;
        }
        moveActor(direction);
    }

    public void dropBomb() {
        if (currentMap.getMapBlock(currentBlock).blockType == GameElement.BlockType.GRASS) {
            Bomb bomb = new Bomb();
            bomb.owner = this;
            bomb.currentMap = currentMap;
            bomb.setBlock(currentBlock);
            currentMap.getMapBlock(bomb.currentBlock).blockType = GameElement.BlockType.BOMB;
            currentMap.getChild("bomb").addChild(bomb);
        }
    }

}

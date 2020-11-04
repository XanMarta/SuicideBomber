package com.suicidebomber.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.engine.MapElement;
import com.suicidebomber.game.GameElement;


public class Player extends Actor {

    public void render() {
        super.render();
        playerMovement();
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            dropBomb();
        }
        checkCollision(currentBlock);
        if (nearbyBlock.x >= 0 && nearbyBlock.y >= 0) {
            checkCollision(nearbyBlock);
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
            bomb.setMap(currentMap, currentBlock);
            currentMap.getChild("bomb").addChild(bomb);
        }
    }

    public void checkCollision(Vector2 pos) {
        System.out.println("Number element in " + pos.toString() + " : " + currentMap.getMapBlock(pos).elements.size);
        if (currentMap.getMapBlock(pos).blockType == GameElement.BlockType.ITEM) {
            for (MapElement element : currentMap.getMapBlock(pos).elements) {
                if (element instanceof Item) {
                    element.disappear();
                }
            }
        }
    }

}

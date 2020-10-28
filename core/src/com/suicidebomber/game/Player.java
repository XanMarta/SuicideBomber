package com.suicidebomber.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;


public class Player extends Actor {

    public void render() {
        super.render();
        playerMovement();
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

}

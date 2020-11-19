package com.suicidebomber.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;


public class Human extends Player {

    public int leftKey = Input.Keys.A;
    public int rightKey = Input.Keys.D;
    public int upKey = Input.Keys.W;
    public int downKey = Input.Keys.S;
    public int bombKey = Input.Keys.J;

    public void render() {
        super.render();
        if (isAppearing) {
            playerMovement();
            if (Gdx.input.isKeyJustPressed(bombKey)) {
                dropBomb();
            }
        }
    }

    public void playerMovement() {
        direction.setZero();
        if (Gdx.input.isKeyPressed(leftKey)) {
            direction.x = -1;
            direction.y = 0;
        }
        if (Gdx.input.isKeyPressed(rightKey)) {
            direction.x = 1;
            direction.y = 0;
        }
        if (Gdx.input.isKeyPressed(upKey)) {
            direction.y = 1;
            direction.x = 0;
        }
        if (Gdx.input.isKeyPressed(downKey)) {
            direction.y = -1;
            direction.x = 0;
        }
        movePlayer(direction);
    }
}

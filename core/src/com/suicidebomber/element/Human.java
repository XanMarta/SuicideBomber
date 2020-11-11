package com.suicidebomber.element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.engine.AnimatedSprite;
import com.suicidebomber.engine.AnimationSprite;


public class Human extends Player {

    public int leftKey = Input.Keys.A;
    public int rightKey = Input.Keys.D;
    public int upKey = Input.Keys.W;
    public int downKey = Input.Keys.S;
    public int bombKey = Input.Keys.J;

    public AnimatedSprite animatedSprite;
    public Vector2 direction = new Vector2(0, 0);

    public Human() {
        super();
        sprite = new AnimatedSprite();
        animatedSprite = (AnimatedSprite) sprite;
        AnimationSprite runAnim = new AnimationSprite();
        runAnim.addSprite("ITEM_BOMB");
        runAnim.addSprite("ITEM_SPEED");
        runAnim.addSprite("ITEM_HEART");
        animatedSprite.addAnimation("RUN", runAnim);
        AnimationSprite standAnim = new AnimationSprite();
        standAnim.addSprite("PLAYER");
        animatedSprite.addAnimation("STAND", standAnim);
        animatedSprite.play("STAND");
    }

    public void render() {
        super.render();
        if (isLiving) {
            playerMovement();
            if (Gdx.input.isKeyJustPressed(bombKey)) {
                dropBomb();
            }
            if (direction.isZero()) {
                animatedSprite.pause();
            } else {
                animatedSprite.play("RUN");
            }
        }
    }

    public void playerMovement() {
        direction = new Vector2().setZero();
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

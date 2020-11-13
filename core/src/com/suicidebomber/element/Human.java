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
        animatedSprite = new AnimatedSprite();
        renderElement.add(animatedSprite);
        addChild(animatedSprite);

        AnimationSprite upAnim = new AnimationSprite();
        upAnim.addSprite("BLUE_UP_1");
        upAnim.addSprite("BLUE_UP_2");
        upAnim.addSprite("BLUE_UP_3");
        upAnim.addSprite("BLUE_UP_4");
        upAnim.addSprite("BLUE_UP_5");
        upAnim.delay = 100;
        animatedSprite.addAnimation("UP", upAnim);

        AnimationSprite leftAnim = new AnimationSprite();
        leftAnim.addSprite("BLUE_LEFT_1");
        leftAnim.addSprite("BLUE_LEFT_2");
        leftAnim.addSprite("BLUE_LEFT_3");
        leftAnim.addSprite("BLUE_LEFT_4");
        leftAnim.addSprite("BLUE_LEFT_5");
        leftAnim.delay = 100;
        animatedSprite.addAnimation("LEFT", leftAnim);

        AnimationSprite rightAnim = new AnimationSprite();
        rightAnim.addSprite("BLUE_RIGHT_1");
        rightAnim.addSprite("BLUE_RIGHT_2");
        rightAnim.addSprite("BLUE_RIGHT_3");
        rightAnim.addSprite("BLUE_RIGHT_4");
        rightAnim.addSprite("BLUE_RIGHT_5");
        rightAnim.delay = 100;
        animatedSprite.addAnimation("RIGHT", rightAnim);

        AnimationSprite downAnim = new AnimationSprite();
        downAnim.addSprite("BLUE_DOWN_1");
        downAnim.addSprite("BLUE_DOWN_2");
        downAnim.addSprite("BLUE_DOWN_3");
        downAnim.addSprite("BLUE_DOWN_4");
        downAnim.addSprite("BLUE_DOWN_5");
        downAnim.delay = 100;
        animatedSprite.addAnimation("DOWN", downAnim);

        AnimationSprite normalAnim = new AnimationSprite();
        normalAnim.addSprite("BLUE_NORMAL");

        animatedSprite.addAnimation("NORMAL", normalAnim);
        animatedSprite.play("NORMAL");
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
            } else if (direction.epsilonEquals(0, 1)) {
                animatedSprite.play("UP");
            } else if (direction.epsilonEquals(1, 0)) {
                animatedSprite.play("LEFT");
            } else if (direction.epsilonEquals(-1, 0)) {
                animatedSprite.play("RIGHT");
            } else {
                animatedSprite.play("DOWN");
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

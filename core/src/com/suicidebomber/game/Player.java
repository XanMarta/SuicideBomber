package com.suicidebomber.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.element.MapElement;


public class Player extends MapElement {

    public Player() {
        super();
    }

    public void render() {
        super.render();
        Vector2 velocity = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            velocity.x -= 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            velocity.x += 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            velocity.y += 5;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            velocity.y -= 5;
        }
        this.position.add(velocity);
        updateBlock();
        System.out.println("Current block: " + currentBlock.toString());
    }
}

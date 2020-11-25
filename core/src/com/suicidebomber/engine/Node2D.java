package com.suicidebomber.engine;

import com.badlogic.gdx.math.Vector2;


public class Node2D extends Node {

    public Vector2 position = new Vector2();
    public Vector2 global_position = new Vector2();

    public void render() {
        super.render();
        if (getParent() == null || !(getParent() instanceof Node2D)) {
            global_position.set(position);
        } else {
            global_position.set(position).add(((Node2D) getParent()).global_position);
        }
    }
}

package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;


public class Node2D extends Node {

    public Vector2 position = new Vector2();
    public Vector2 global_position = new Vector2();

    public void render() {
        super.render();
        if (parent == null || !(parent instanceof Node2D)) {
            global_position.set(position);
        } else {
            global_position.set(position).add(((Node2D) parent).global_position);
        }
    }
}

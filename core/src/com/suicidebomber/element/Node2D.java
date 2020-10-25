package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;


public class Node2D extends Node {

    public Vector2 position = new Vector2();

    public Vector2 getGlobalPosition() {
        if (parent == null || !(parent instanceof Node2D)) {
            return new Vector2(position);
        } else {
            return new Vector2(position).add(((Node2D) parent).position);
        }
    }
}

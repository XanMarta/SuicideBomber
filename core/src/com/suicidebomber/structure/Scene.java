package com.suicidebomber.structure;

import com.suicidebomber.engine.Node;


public class Scene {

    public Node root = new Node();

    public void create() {

    }

    public void prepare() {
        root.create();
    }

    public void render() {
        root._render();
    }

    public void dispose() {
        root.safefree();
    }
}

package com.suicidebomber;

import com.suicidebomber.element.Node;
import com.suicidebomber.element.Node2D;
import com.suicidebomber.element.Sprite;


public class Testing {

    public Node root;

    public Testing() {
        root = new Node();

        Node object = new Node();
        root.addChild(object);

        Node2D animal = new Node2D() {
            public void render() {
                super.render();
                position.add(1, 1);
            }
        };
        animal.position.set(100, 200);
        object.addChild(animal);

        Sprite sprite = new Sprite();
        sprite.image = "player";
        animal.addChild(sprite);
    }

    public static void main(String args[]) {
        new Testing();
    }

}

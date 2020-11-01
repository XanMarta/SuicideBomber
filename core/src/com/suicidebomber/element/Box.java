package com.suicidebomber.element;

import com.suicidebomber.game.GameElement;
import com.suicidebomber.engine.MapElement;


public class Box extends MapElement {

    public Box() {
        super();
        sprite.image = "BOX";
        blockType = GameElement.BlockType.BOX;
    }

    public void runoff() {
        free();
    }
}

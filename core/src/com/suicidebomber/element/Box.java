package com.suicidebomber.element;

import com.suicidebomber.game.GameElement;
import com.suicidebomber.engine.MapElement;


public class Box extends MapElement {

    public Box() {
        super();
        sprite.image = "BOX";
    }

    public void runoff() {
        currentMap.getMapBlock(currentBlock).blockType = GameElement.BlockType.GRASS;
        free();
    }
}

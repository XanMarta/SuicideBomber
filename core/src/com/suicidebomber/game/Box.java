package com.suicidebomber.game;

import com.suicidebomber.GameElement;
import com.suicidebomber.element.MapElement;


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

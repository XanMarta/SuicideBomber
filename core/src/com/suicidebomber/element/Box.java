package com.suicidebomber.element;

import com.suicidebomber.game.GameElement;
import com.suicidebomber.engine.MapElement;

import java.util.Random;


public class Box extends MapElement {

    public Box() {
        super();
        sprite.image = "BOX";
        blockType = GameElement.BlockType.BOX;
    }

    public void disappear() {
        super.disappear();
        Random random = new Random();
        if (random.nextInt(100) < 40) {
            Item item = new Item();
            item.setMap(currentMap);
            item.setBlock(currentBlock);
            currentMap.getChild("item").addChild(item);
        }
        safefree();
    }
}

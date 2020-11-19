package com.suicidebomber.element;

import com.suicidebomber.engine.Sprite;
import com.suicidebomber.structure.GameElement;
import com.suicidebomber.engine.MapElement;


public class Box extends MapElement {

    public Box() {
        super();
        blockType = GameElement.BlockType.BOX;
    }

    public void create() {
        super.create();
        Sprite sprite = new Sprite();
        sprite.image = "BOX";
        addChild(sprite);
        renderElement.add(sprite);
    }

    public void disappear() {
        super.disappear();
        if (GameElement.random.nextInt(100) < 40) {
            Item item = new Item();
            item.setMap(currentMap);
            item.setBlock(currentBlock);
            currentMap.getChild("item").addChild(item);
        }
        safefree();
    }
}

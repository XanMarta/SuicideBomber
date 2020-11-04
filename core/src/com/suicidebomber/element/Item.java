package com.suicidebomber.element;

import com.suicidebomber.engine.MapElement;
import com.suicidebomber.game.GameElement;


public class Item extends MapElement {

    public Item() {
        super();
        sprite.image = "ITEM";
        blockType = GameElement.BlockType.ITEM;
    }

    public void disappear() {
        super.disappear();
        free();
    }
}

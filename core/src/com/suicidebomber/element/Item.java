package com.suicidebomber.element;

import com.suicidebomber.engine.MapElement;
import com.suicidebomber.game.GameElement;
import java.util.Random;


public class Item extends MapElement {

    public String item_name = "";

    public Item() {
        super();
        blockType = GameElement.BlockType.ITEM;
    }

    public void create() {
        super.create();
        Random random = new Random();
        int key = random.nextInt(100);
        if (key < 25) {
            item_name = "BOMB";
        } else if (key < 50) {
            item_name = "HEART";
        } else if (key < 75) {
            item_name = "POWER";
        } else {
            item_name = "SPEED";
        }
        sprite.image = "ITEM_" + item_name;
    }

    public void disappear() {
        super.disappear();
        free();
    }
}

package com.suicidebomber.element;

import com.suicidebomber.engine.MapElement;
import com.suicidebomber.engine.Sprite;
import com.suicidebomber.source.manager.GameHelper;
import com.suicidebomber.autoload.GameElement;


public class Item extends MapElement {

    public String item_name = "";

    private Sprite sprite;

    public Item() {
        super();
        blockType = GameElement.BlockType.ITEM;
        initScore = 0.5f;
    }

    public void create() {
        super.create();
        int key = GameHelper.instance().random.nextInt(100);
        if (key < 25) {
            item_name = "BOMB";
        } else if (key < 50) {
            item_name = "HEART";
        } else if (key < 75) {
            item_name = "POWER";
        } else {
            item_name = "SPEED";
        }
        sprite = new Sprite();
        sprite.image = "ITEM_" + item_name;
        renderElement.add(sprite);
        addChild(sprite);
    }

    public void disappear() {
        super.disappear();
        safefree();
    }
}

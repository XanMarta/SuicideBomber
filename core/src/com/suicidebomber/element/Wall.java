package com.suicidebomber.element;

import com.suicidebomber.engine.MapElement;
import com.suicidebomber.engine.Sprite;
import com.suicidebomber.structure.GameElement;


public class Wall extends MapElement {

    private Sprite sprite;

    public Wall() {
        super();
        sprite = new Sprite();
        sprite.image = "WALL";
        renderElement.add(sprite);
        addChild(sprite);
        blockType = GameElement.BlockType.WALL;
        initScore = -1.0f;
    }
}

package com.suicidebomber.element;

import com.suicidebomber.engine.MapElement;
import com.suicidebomber.game.GameElement;


public class Wall extends MapElement {

    public Wall() {
        super();
        sprite.image = "WALL";
        blockType = GameElement.BlockType.WALL;
    }
}

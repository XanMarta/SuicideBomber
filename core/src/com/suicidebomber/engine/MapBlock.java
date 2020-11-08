package com.suicidebomber.engine;

import com.badlogic.gdx.utils.Array;
import com.suicidebomber.game.GameElement;


public class MapBlock extends Node2D {

    public GameElement.BlockType blockType = GameElement.BlockType.GRASS;
    public Array<MapElement> newelements = new Array<>();
    public Array<MapElement> elements = new Array<>();

    public void render() {
        super.render();
        elements.clear();
        for (MapElement element : newelements) {
            elements.add(element);
        }
        newelements.clear();
    }
}

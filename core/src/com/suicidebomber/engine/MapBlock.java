package com.suicidebomber.engine;

import com.badlogic.gdx.utils.Array;
import com.suicidebomber.structure.GameElement;


public class MapBlock extends Node2D {

    public GameElement.BlockType blockType = GameElement.BlockType.GRASS;
    public Array<MapElement> newelements = new Array<>();
    // Accessible from outside
    public Array<MapElement> elements = new Array<>();
    public float score = 0.0f;

    public void render() {
        super.render();
        elements.clear();
        for (MapElement element : newelements) {
            elements.add(element);
        }
        score = 0.0f;
        newelements.clear();
    }
}

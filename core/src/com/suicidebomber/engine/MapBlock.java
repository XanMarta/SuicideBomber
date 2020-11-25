package com.suicidebomber.engine;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.suicidebomber.autoload.GameElement;


public class MapBlock extends Node2D {

    public Vector2 currentBlock = new Vector2(0, 0);
    public GameElement.BlockType blockType = GameElement.BlockType.GRASS;
    public Array<MapElement> elements = new Array<>();           // Element to read
    private Array<MapElement> newelements = new Array<>();       // Element to write

    public void render() {
        super.render();
        elements.clear();
        for (MapElement element : newelements) {
            elements.add(element);
        }
        newelements.clear();
    }

    public void addElement(MapElement element) {
        newelements.add(element);
    }
}

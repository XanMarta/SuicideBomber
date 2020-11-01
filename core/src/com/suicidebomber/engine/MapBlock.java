package com.suicidebomber.engine;

import com.badlogic.gdx.utils.Array;
import com.suicidebomber.game.GameElement;


public class MapBlock extends Node2D {

    public GameElement.BlockType blockType = GameElement.BlockType.GRASS;
    public static String defaultImage = "GRASS";
    public Array<MapElement> elements = new Array<>();

    public void render() {
        super.render();
        GameElement.imageManager.drawImage(defaultImage, global_position);
        for (MapElement element : elements) {
            element.sprite.renderImage();
        }
        elements.clear();
    }
}

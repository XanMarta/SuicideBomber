package com.suicidebomber.element;

import com.badlogic.gdx.utils.Array;
import com.suicidebomber.GameElement;


public class MapBlock extends Node2D {

    public GameElement.BlockType blockType = GameElement.BlockType.GRASS;
    private String image = "";
    public Array<MapElement> elements = new Array<>();

    public void setBlockType(GameElement.BlockType blockType) {
        this.blockType = blockType;
        if (blockType == GameElement.BlockType.GRASS) {
            image = "GRASS";
        } else if (blockType == GameElement.BlockType.WALL) {
            image = "WALL";
        }
    }

    public void render() {
        super.render();
        if (!image.equals("")) {
            GameElement.imageManager.drawImage(image, global_position);
        }
        for (MapElement element : elements) {
            element.sprite.renderImage();
        }
        elements.clear();
    }
}

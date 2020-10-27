package com.suicidebomber.element;

import com.suicidebomber.GameElement;
import java.util.ArrayList;


public class MapBlock extends Node2D {

    public GameElement.BlockType blockType = GameElement.BlockType.GRASS;
    private String image = "";
    public ArrayList<Sprite> element = new ArrayList<>();

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
        for (Sprite sprite : element) {
            sprite.renderImage();
        }
        element.clear();
    }
}

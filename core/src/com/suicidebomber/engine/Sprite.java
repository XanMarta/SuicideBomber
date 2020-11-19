package com.suicidebomber.engine;

import com.suicidebomber.structure.GameElement;


public class Sprite extends Canvas2D {

    public String image = "";

    public void render() {
        super.render();
        if (!image.equals("")) {
            if (getVisible()) {
                renderImage();
            }
        }
    }

    public void renderImage() {
        GameElement.imageManager.drawImage(image, global_position, getAlpha());
    }
}

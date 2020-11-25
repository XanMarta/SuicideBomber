package com.suicidebomber.engine;

import com.suicidebomber.source.ImageManager;


public class Sprite extends Canvas2D {

    public String image = "";

    public void render() {
        super.render();
        if (getVisible()) {
            renderImage();
        }
    }

    public void renderImage() {
        if (!image.equals("")) {
            ImageManager.instance().drawImage(image, global_position, getAlpha());
        }
    }
}

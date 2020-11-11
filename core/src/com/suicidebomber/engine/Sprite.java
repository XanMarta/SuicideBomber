package com.suicidebomber.engine;

import com.suicidebomber.game.GameElement;


public class Sprite extends Canvas2D {

    public String image = "";
    public boolean showing = true;

    public void render() {
        super.render();
        if (showing && !image.equals("")) {
            renderImage();
        }
    }

    public void renderImage() {
        GameElement.imageManager.drawImage(image, global_position, getAlpha());
    }
}

package com.suicidebomber.engine;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.game.GameElement;


public class Sprite extends Node2D {

    public String image = "";
    public Vector2 size = null;
    public boolean showing = true;

    public void render() {
        super.render();
        if (showing && !image.equals("")) {
            renderImage();
        }
    }

    public void renderImage() {
        if (size == null) {
            GameElement.imageManager.drawImage(image, global_position);
        } else {
            GameElement.imageManager.drawImage(image, global_position, size);
        }
    }
}

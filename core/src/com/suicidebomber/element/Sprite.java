package com.suicidebomber.element;

import com.suicidebomber.GameElement;


public class Sprite extends Node2D {

    public String image = "";

    public void render() {
        if (!image.equals("")) {
            GameElement.imageManager.drawImage(image, getGlobalPosition());
        }
    }
}

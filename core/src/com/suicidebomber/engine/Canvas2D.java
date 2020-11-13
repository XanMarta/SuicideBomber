package com.suicidebomber.engine;


public class Canvas2D extends Node2D {

    public boolean visible = true;
    public float alpha = 1;

    public boolean getVisible() {
        if (parent != null) {
            if (parent instanceof Canvas2D) {
                return (((Canvas2D) parent).getVisible() && this.visible);
            }
        }
        return this.visible;
    }

    public float getAlpha() {
        if (parent != null) {
            if (parent instanceof Canvas2D) {
                return (((Canvas2D) parent).getAlpha() * this.alpha);
            }
        }
        return this.alpha;
    }

    public void show() {
        visible = true;
    }

    public void hide() {
        visible = false;
    }
}

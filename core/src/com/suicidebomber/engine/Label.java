package com.suicidebomber.engine;

import com.suicidebomber.structure.GameElement;

public class Label extends Canvas2D {

    public String text = "";
    public String font = GameElement.defaultFont;
    public boolean center = false;

    public void render() {
        super.render();
        if (getVisible()) {
            GameElement.fontManager.drawText(font, text, global_position, center);
        }
    }
}

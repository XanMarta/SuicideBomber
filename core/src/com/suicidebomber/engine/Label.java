package com.suicidebomber.engine;

import com.suicidebomber.game.GameElement;

public class Label extends Canvas2D {

    public String text = "";

    public void render() {
        super.render();
        if (getVisible()) {
            GameElement.fontManager.drawText(GameElement.defaultFont, text, global_position);
        }
    }
}

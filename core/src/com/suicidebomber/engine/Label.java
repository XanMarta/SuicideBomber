package com.suicidebomber.engine;

import com.suicidebomber.source.manager.FontManager;
import com.suicidebomber.autoload.GameElement;


public class Label extends Canvas2D {

    public String text = "";
    public String font = GameElement.defaultFont;
    public boolean center = false;

    public void render() {
        super.render();
        if (getVisible()) {
            FontManager.instance().drawText(font, text, global_position, center);
        }
    }
}

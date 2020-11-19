package com.suicidebomber.source;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.structure.GameElement;

import java.util.HashMap;


public class FontManager {

    public HashMap<String, BitmapFont> fonts = new HashMap<>();

    public void create() {
        loadFont("COMIC", "font/comic.fnt", "font/comic_0.png");
    }

    public void dispose() {
        for (String font : fonts.keySet()) {
            fonts.get(font).dispose();
        }
    }

    public void drawText(String font, String text, Vector2 position) {
        if (fonts.containsKey(font)) {
            fonts.get(font).draw(GameElement.batch, text, position.x, position.y);
        }
    }

    private void loadFont(String font, String fntPath, String pngPath) {
        try {
            BitmapFont newFont = new BitmapFont(Gdx.files.internal(fntPath), Gdx.files.internal(pngPath), false);
            fonts.put(font, newFont);
        } catch (Exception e) {
            System.out.println("Cannot load font " + fntPath + " + " + pngPath);
        }
    }
}
package com.suicidebomber.source.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;
import java.util.HashMap;


public class FontManager {

    private static FontManager fontManager = null;
    private HashMap<String, BitmapFont> fonts = new HashMap<>();

    private FontManager() {

    }

    public static FontManager instance() {
        if (fontManager == null) {
            fontManager = new FontManager();
        }
        return fontManager;
    }

    public void create() {
        loadFont("COMIC", "font/comic.fnt", "font/comic_0.png");
        loadFont("COMIC_LARGE", "font/comic_large.fnt", "font/comic_large.png");
        fonts.get("COMIC_LARGE").setColor(Color.GOLDENROD);
    }

    public void dispose() {
        for (String font : fonts.keySet()) {
            fonts.get(font).dispose();
        }
    }

    public void drawText(String font, String text, Vector2 position, boolean center) {
        if (fonts.containsKey(font)) {
            if (center) {
                GlyphLayout layout = fonts.get(font).getCache().addText(text, position.x, position.y);
                fonts.get(font).draw(ImageManager.batch, text, position.x - layout.width / 2, position.y);
            } else {
                fonts.get(font).draw(ImageManager.batch, text, position.x, position.y);
            }
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
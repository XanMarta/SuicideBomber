package com.suicidebomber.source;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.GameElement;
import java.util.HashMap;


public class ImageManager {

    private HashMap<String, Image> images;
    private SpriteBatch batch;

    public void create() {
        images = new HashMap<>();
        batch = new SpriteBatch();
        images.put("player", new Image(new Texture("image.png"), GameElement.blockSize));
    }

    public void dispose() {
        for (String name : images.keySet()) {
            images.get(name).texture.dispose();
        }
        batch.dispose();
    }

    public void startDraw() {
        batch.begin();
    }

    public void endDraw() {
        batch.end();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void drawImage(String image, float x, float y) {
        if (images.containsKey(image)) {
            Image imageDraw = images.get(image);
            batch.draw(imageDraw.texture, x, y, imageDraw.size.x, imageDraw.size.y);
        }
    }
}


class Image {

    protected Texture texture;
    protected Vector2 size;

    public Image(Texture texture, Vector2 size) {
        this.texture = texture;
        this.size = size;
    }
}
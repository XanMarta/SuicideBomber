package com.suicidebomber.source;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.game.GameElement;

import java.util.HashMap;


public class ImageManager {

    private HashMap<String, Image> images;
    private SpriteBatch batch;

    public void create() {
        images = new HashMap<>();
        batch = new SpriteBatch();
        loadImage("IMAGE", "image/image.png", GameElement.blockSize);
        loadImage("WALL", "image/wall.png", GameElement.blockSize.x, GameElement.blockSize.y * 2);
        loadImage("GRASS", "image/grass.png", GameElement.blockSize);
        loadImage("PLAYER", "image/player.png", GameElement.blockSize);
        loadImage("FIRE", "image/fire.png", GameElement.blockSize);
        loadImage("BOX", "image/box.png", GameElement.blockSize);
    }

    public void dispose() {
        for (String name : images.keySet()) {
            images.get(name).texture.dispose();
        }
        batch.dispose();
    }

    private void loadImage(String image, String path, Vector2 size) {
        images.put(image, new Image(new Texture(path), size));
    }

    private void loadImage(String image, String path, float x, float y) {
        images.put(image, new Image(new Texture(path), new Vector2(x, y)));
    }

    public void startDraw() {
        batch.begin();
    }

    public void endDraw() {
        batch.end();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void drawImage(String image, float x, float y) {
        if (images.containsKey(image)) {
            Image imageDraw = images.get(image);
            batch.draw(imageDraw.texture, x, y, imageDraw.size.x, imageDraw.size.y);
        }
    }

    public void drawImage(String image, Vector2 position, Vector2 size) {
        if (images.containsKey(image)) {
            Image imageDraw = images.get(image);
            batch.draw(imageDraw.texture, position.x, position.y, size.x, size.y);
        }
    }

    public void drawImage(String image, Vector2 position) {
        if (images.containsKey(image)) {
            Image imageDraw = images.get(image);
            batch.draw(imageDraw.texture, position.x, position.y);
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
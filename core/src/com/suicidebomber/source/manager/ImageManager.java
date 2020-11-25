package com.suicidebomber.source.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class ImageManager {

    private static ImageManager imageManager = null;
    private HashMap<String, Texture> images;

    public static SpriteBatch batch;

    private ImageManager() {

    }

    public static ImageManager instance() {
        if (imageManager == null) {
            imageManager = new ImageManager();
        }
        return imageManager;
    }

    public void create() {
        images = new HashMap<>();
        batch = new SpriteBatch();
        loadImageFile("core/assets/image.dll");
    }

    public void dispose() {
        for (String name : images.keySet()) {
            images.get(name).dispose();
        }
    }

    private void loadImageFile(String path) {              // Load image from file.       name|path
        try {
            Scanner scan = new Scanner(new File(path));
            while (scan.hasNextLine()) {
                String[] temp = scan.nextLine().split("\\|");
                if (temp.length == 2) {
                    loadImage(temp[0].trim(), temp[1].trim());
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        }
    }

    private void loadImage(String image, String path) {
        try {
            Texture texture = new Texture(path);
            images.put(image, texture);
        } catch (GdxRuntimeException e) {
            System.out.println("File not found: " + path);
        }
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void drawImage(String image, Vector2 position) {
        if (images.containsKey(image)) {
            batch.draw(images.get(image), position.x, position.y);
        }
    }

    public void drawImage(String image, Vector2 position, float alpha) {
        if (alpha == 1) {
            drawImage(image, position);
        } else if (images.containsKey(image)) {
            batch.setColor(1, 1, 1, alpha);
            batch.draw(images.get(image), position.x, position.y);
            batch.setColor(1, 1, 1, 1);
        }
    }
}

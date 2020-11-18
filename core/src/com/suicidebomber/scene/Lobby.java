package com.suicidebomber.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.suicidebomber.engine.Canvas2D;
import com.suicidebomber.engine.Sprite;
import com.suicidebomber.structure.GameElement;
import com.suicidebomber.structure.Scene;


public class Lobby extends Scene {

    public void create() {
        super.create();
        for (int i = 1; i <= 5; i++) {
            Sprite sprite = new Sprite();
            sprite.image = "FIRE";
            sprite.position.set(100 * i, 50 * i + 100);
            root.addChild(sprite);
        }

        Canvas2D player = new Canvas2D() {
            public void render() {
                super.render();
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    GameElement.sceneManager.changeSceneTo(new PlayGround());
                }
            }
        };
        root.addChild(player);
    }
}

package com.suicidebomber.scene;

import com.suicidebomber.engine.Button;
import com.suicidebomber.engine.Node;
import com.suicidebomber.engine.Sprite;
import com.suicidebomber.structure.GameElement;
import com.suicidebomber.structure.Scene;


public class Lobby extends Scene {

    public void create() {
        root = new Node() {
            public void execute_signal(String signal) {
                super.execute_signal(signal);
                if (signal.equals("startButtonPressed")) {
                    startGame();
                } else if (signal.equals("quitButtonPressed")) {
                    endGame();
                }
            }
        };

        super.create();
        Sprite sprite = new Sprite();
        sprite.image = "START_SCENE";
        root.addChild(sprite);

        Button startButton = new Button();
        startButton.mouseInTexture = "BUTTON_START";
        startButton.position.set(769, 280);
        startButton.size.set(430, 196);
        startButton.connect_signal("button_pressed", root, "startButtonPressed");
        root.addChild(startButton);

        Button quitButton = new Button();
        quitButton.mouseInTexture = "BUTTON_QUIT";
        quitButton.position.set(769, 42);
        quitButton.size.set(430, 196);
        quitButton.connect_signal("button_pressed", root, "quitButtonPressed");
        root.addChild(quitButton);
    }

    public void startGame() {
        GameElement.sceneManager.changeSceneTo((new PlayGround()));
    }

    public void endGame() {
        GameElement.sceneManager.exitGame();
    }
}

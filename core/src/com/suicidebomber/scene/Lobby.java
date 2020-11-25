package com.suicidebomber.scene;

import com.suicidebomber.engine.Button;
import com.suicidebomber.engine.Node;
import com.suicidebomber.engine.Sprite;
import com.suicidebomber.source.manager.SceneManager;
import com.suicidebomber.source.Scene;


public class Lobby extends Scene {

    private TransitionScene transitionScene;
    private boolean isQuitting = false;

    public void create() {
        root = new Node() {
            public void execute_signal(String signal) {
                super.execute_signal(signal);
                if (signal.equals("startButtonPressed")) {
                    startButton();
                } else if (signal.equals("quitButtonPressed")) {
                    quitButton();
                } else if (signal.equals("disappear_ended")) {
                    animEnded();
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

        transitionScene = new TransitionScene();
        root.addChild(transitionScene);
        transitionScene.appear();
        transitionScene.connect_signal("disappear_ended", root, "disappear_ended");
    }

    public void startButton() {
        transitionScene.disappear();
    }

    public void quitButton() {
        transitionScene.disappear();
        isQuitting = true;
    }

    public void animEnded() {
        if (isQuitting) {
            endGame();
        } else {
            startGame();
        }
    }

    public void startGame() {
        SceneManager.instance().changeSceneTo((new PlayGround()));
    }

    public void endGame() {
        SceneManager.instance().exitGame();
    }
}

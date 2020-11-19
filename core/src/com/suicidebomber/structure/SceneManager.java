package com.suicidebomber.structure;

import com.badlogic.gdx.Gdx;
import com.suicidebomber.engine.Node;
import com.suicidebomber.scene.Lobby;


public class SceneManager {

    public Scene currentScene = null;
    private Node node;
    private Scene upcomingScene = null;

    public SceneManager() {
        node = new Node() {
            public void execute_signal(String signal) {
                super.execute_signal(signal);
                if (signal.equals("safefree")) {
                    changeScene();
                }
            }
        };
    }

    public void create() {
        setScene(new Lobby());     // MainScene
    }

    public void render() {
        currentScene.render();
    }

    public void dispose() {
        currentScene.dispose();
        currentScene = null;
    }

    public void changeSceneTo(Scene scene) {
        upcomingScene = scene;
        currentScene.root.isSafeFree = true;
    }

    public void exitGame() {
        changeSceneTo(null);
    }

    private void setScene(Scene scene) {
        currentScene = scene;
        currentScene.create();
        currentScene.prepare();
        currentScene.root.connect_signal("safefree", node, "safefree");
    }

    protected void changeScene() {
        if (upcomingScene != null) {
            currentScene.dispose();
            setScene(upcomingScene);
            upcomingScene = null;
        } else {
            Gdx.app.exit();
        }
    }
}

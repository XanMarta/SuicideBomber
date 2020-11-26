package com.suicidebomber.source.manager;

import com.badlogic.gdx.Gdx;
import com.suicidebomber.autoload.Autoload;
import com.suicidebomber.engine.Node;
import com.suicidebomber.scene.Lobby;
import com.suicidebomber.source.Scene;


public class SceneManager {

    private static SceneManager sceneManager = null;
    private Scene currentScene = null;
    private Node node;
    private Scene upcomingScene = null;
    public static Autoload autoload;

    private SceneManager() {

    }

    public static SceneManager instance() {
        if (sceneManager == null) {
            sceneManager = new SceneManager();
        }
        return sceneManager;
    }

    public void create() {
        node = new Node() {
            public void execute_signal(String signal) {
                super.execute_signal(signal);
                if (signal.equals("safefree")) {
                    changeScene();
                }
            }
        };
        autoload = new Autoload();
        autoload.create();
        setScene(new Lobby());     // MainScene
    }

    public void render() {
        autoload.render();
        currentScene.render();
    }

    public void dispose() {
        autoload.dispose();
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

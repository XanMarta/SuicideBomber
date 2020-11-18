package com.suicidebomber.structure;

import com.suicidebomber.scene.Lobby;
import com.suicidebomber.scene.PlayGround;


public class SceneManager {

    public Scene currentScene = null;

    public void create() {              // MainScene
        setScene(new Lobby());
    }

    public void render() {
        currentScene.render();
    }

    public void dispose() {
        currentScene.dispose();
        currentScene = null;
    }

    public void changeSceneTo(Scene scene) {
        currentScene.dispose();
        setScene(scene);
    }

    public void setScene(Scene scene) {
        currentScene = scene;
        currentScene.create();
        currentScene.prepare();
    }
}

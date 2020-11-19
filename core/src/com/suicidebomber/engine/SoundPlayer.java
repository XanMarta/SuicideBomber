package com.suicidebomber.engine;

import com.suicidebomber.structure.GameElement;


public class SoundPlayer extends Node {

    public String currentSound = "";
    public long currentCode = -1;
    public boolean looping = false;

    public void play(String sound) {
        currentCode = GameElement.soundManager.play(sound, looping);
        if (currentCode != -1) {
            currentSound = sound;
        }
    }

    public void stop() {
        GameElement.soundManager.stop(currentSound, currentCode);
    }

    public void dispose() {
        super.dispose();
        stop();
    }
}

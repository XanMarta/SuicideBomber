package com.suicidebomber.engine;

import com.suicidebomber.source.manager.SoundManager;


public class SoundPlayer extends Node {

    public boolean looping = false;
    public boolean stopWhenDispose = true;

    private String currentSound = "";
    private long currentCode = -1;

    public void play(String sound) {
        currentCode = SoundManager.instance().play(sound, looping);
        if (currentCode != -1) {
            currentSound = sound;
        }
    }

    public void stop() {
        SoundManager.instance().stop(currentSound, currentCode);
    }

    public void dispose() {
        super.dispose();
        if (stopWhenDispose) {
            stop();
        }
    }
}

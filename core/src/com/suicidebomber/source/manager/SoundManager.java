package com.suicidebomber.source.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.HashMap;


public class SoundManager {

    private static SoundManager soundManager = null;
    private HashMap<String, Sound> sounds = new HashMap<>();

    private SoundManager() {

    }

    public static SoundManager instance() {
        if (soundManager == null) {
            soundManager = new SoundManager();
        }
        return soundManager;
    }

    public void create() {
        loadSound("BOMB_TIMER", "sound/bomb_timer.wav");
        loadSound("BOOM", "sound/boom.wav");
        loadSound("CHOOSE", "sound/choose.wav");
        loadSound("MUSIC", "sound/music.wav");
        loadSound("READY", "sound/ready.wav");
    }

    public void dispose() {
        for (String sound : sounds.keySet()) {
            sounds.get(sound).dispose();
        }
    }

    public long play(String sound, boolean looping) {
        if (sounds.containsKey(sound)) {
            if (looping) {
                return sounds.get(sound).loop();
            } else {
                return sounds.get(sound).play();
            }
        }
        return -1;
    }

    public void pause(String sound, long code) {
        if (sounds.containsKey(sound)) {
            sounds.get(sound).pause(code);
        }
    }

    public void resume(String sound, long code) {
        if (sounds.containsKey(sound)) {
            sounds.get(sound).resume(code);
        }
    }

    public void stop(String sound, long code) {
        if (sounds.containsKey(sound)) {
            sounds.get(sound).stop(code);
        }
    }

    private void loadSound(String sound, String path) {
        try {
            Sound newSound = Gdx.audio.newSound(Gdx.files.internal(path));
            sounds.put(sound, newSound);
        } catch (Exception e) {
            System.out.println("Cannot load sound " + path + ". Error: " + e.toString());
        }
    }

}

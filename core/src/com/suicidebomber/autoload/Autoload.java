package com.suicidebomber.autoload;

import com.suicidebomber.engine.Node;
import com.suicidebomber.engine.SoundPlayer;


public class Autoload extends Node {

    private SoundPlayer soundPlayer;

    public void create() {
        super.create();
        soundPlayer = new SoundPlayer();
        soundPlayer.looping = true;
        addChild(soundPlayer);

        soundPlayer.play("MUSIC");
    }
}

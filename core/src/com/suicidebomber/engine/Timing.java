package com.suicidebomber.engine;

import com.badlogic.gdx.utils.TimeUtils;

// Signal: time_out

public class Timing extends Node {

    public long wait_time = 1000;
    public boolean looping = false;

    private long startTime = 0;
    private boolean isPlaying = false;
    private boolean forceStop = false;

    public void start() {
        if (!isPlaying) {
            isPlaying = true;
            startTime = TimeUtils.millis();
        }
    }

    public void render() {
        super.render();
        if (isPlaying) {
            if (TimeUtils.timeSinceMillis(startTime) >= wait_time) {
                isPlaying = false;
                emit_signal("time_out");
                if (looping) {
                    if (!forceStop) {
                        start();
                    }
                    forceStop = false;
                }
            }
        }
    }

    public void stop() {
        isPlaying = false;
        forceStop = true;
    }

}
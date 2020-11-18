package com.suicidebomber.engine;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

// Signal: time_out

public class Timing extends Node {

    public float wait_time = 1.0f;
    public boolean isLooping = false;

    private Task task = null;

    public void start() {
        task = new Task() {
            @Override
            public void run() {
                emit_signal("time_out");
                if (isLooping) {
                    start();
                }
            }
        };
        Timer.schedule(task, wait_time);
    }

    public void stop() {
        if (task != null) {
            task.cancel();
        }
    }

    public void dispose() {
        super.dispose();
        stop();
    }
}
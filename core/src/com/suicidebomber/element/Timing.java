package com.suicidebomber.element;

import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

// Signal: time_out

public class Timing extends Node {

    public float wait_time = 1.0f;
    public boolean isLooping = false;

    private Task task;

    public Timing() {
        super();
        task = new Task() {
            @Override
            public void run() {
                emit_signal("time_out");
                if (isLooping) {
                    start();
                }
            }
        };
    }

    public void start() {
        Timer.schedule(task, wait_time);
    }

    public void stop() {
        task.cancel();
    }
}
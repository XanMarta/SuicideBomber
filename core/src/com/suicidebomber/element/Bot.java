package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.engine.Timing;

import java.util.ArrayList;


public class Bot extends Player {

    protected Timing delayBot;
    protected boolean isDelaying = false;
    protected ArrayList<Vector2> availableWay;
    protected ArrayList<Vector2> legitWay;

    public void create() {
        super.create();
        delayBot = new Timing();
        delayBot.wait_time = 500;
        delayBot.connect_signal("time_out", this, "bot_enddelay");
        addChild(delayBot);

        availableWay = new ArrayList<>();
        legitWay = new ArrayList<>();
    }

    public void render() {
        super.render();
        botMovement();
    }

    protected void botStartDelay(long time) {
        delayBot.wait_time = time;
        delayBot.start();
        isDelaying = true;
    }

    protected void botEndDelay() {
        isDelaying = false;
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("bot_enddelay")) {
            botEndDelay();
        }
    }

    // # Override method

    public void botMovement() {

    }

    public void botFindway() {

    }

    public void botDropBomb() {

    }
}

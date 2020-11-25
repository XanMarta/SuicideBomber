package com.suicidebomber.element;

import com.suicidebomber.engine.Timing;


public class Bot extends Player {

    protected Timing delayBot;
    protected boolean isDelaying = false;

    public void create() {
        super.create();
        delayBot = new Timing();
        delayBot.wait_time = 500;
        delayBot.connect_signal("time_out", this, "bot_enddelay");
        addChild(delayBot);
    }

    public void render() {
        super.render();
        botMovement();
    }

    public void botMovement() {

    }

    public void botFindway() {

    }

    public void botStartDelay(long time) {
        delayBot.wait_time = time;
        delayBot.start();
        isDelaying = true;
    }

    public void botEndDelay() {
        isDelaying = false;
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("bot_enddelay")) {
            botEndDelay();
        }
    }
}

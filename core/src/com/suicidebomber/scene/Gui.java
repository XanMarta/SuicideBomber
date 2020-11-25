package com.suicidebomber.scene;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.engine.Canvas2D;
import com.suicidebomber.engine.Label;
import com.suicidebomber.engine.Timing;
import com.suicidebomber.autoload.GameElement;

// Signal: time_out, text_out

public class Gui extends Canvas2D {

    private Label timerLabel;
    private Timing timerCount;
    private Label textLabel;
    private Timing textCount;
    private int remainTime;

    public void create() {
        super.create();
        //
        timerLabel = new Label();
        timerLabel.font = GameElement.defaultFont;
        timerLabel.position.set(130, 940);
        addChild(timerLabel);
        //
        timerCount = new Timing();
        timerCount.wait_time = 1000;
        timerCount.looping = true;
        timerCount.connect_signal("time_out", this, "timer_count");
        addChild(timerCount);
        //
        textLabel = new Label();
        textLabel.font = "COMIC_LARGE";
        textLabel.position.set(new Vector2(GameElement.windowsSize).scl(0.5f));
        textLabel.visible = false;
        addChild(textLabel);
        //
        textCount = new Timing();
        textCount.wait_time = 3000;
        textCount.connect_signal("time_out", this, "text_out");
        addChild(textCount);
        //
        remainTime = GameElement.matchTime;
        countTime();
        timerCount.start();
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("timer_count")) {
            countTime();
        } else if (signal.equals("text_out")) {
            text_out();
        }
    }

    private void countTime() {
        remainTime -= 1;
        if (remainTime < 0) {
            timerCount.stop();
            emit_signal("time_out");
            return;
        }
        int minute = remainTime / 60;
        int second = remainTime - minute * 60;
        String result = "";
        if (minute < 10) {
            result += "0";
        }
        result += minute + ":";
        if (second < 10) {
            result += "0";
        }
        result += second;
        timerLabel.text = result;
    }

    public void show_text(String text) {
        textLabel.text = text;
        textLabel.visible = true;
        textCount.start();
    }

    protected void text_out() {
        textLabel.visible = false;
        emit_signal("text_out");
    }
}

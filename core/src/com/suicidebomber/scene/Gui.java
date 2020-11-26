package com.suicidebomber.scene;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.engine.*;
import com.suicidebomber.autoload.GameElement;

// Signal: time_out, text_out

public class Gui extends Canvas2D {

    private Label timerLabel;
    private Timing timerCount;
    private Label textLabel;
    private Timing textCount;
    private Sprite winSprite;
    private int remainTime;
    private SoundPlayer soundPlayer;

    public void create() {
        super.create();
        //
        winSprite = new Sprite();
        winSprite.image = "WIN_SPRITE";
        winSprite.alpha = 0.5f;
        winSprite.visible = false;
        addChild(winSprite);
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
        textLabel.position.set(new Vector2(GameElement.windowsSize).scl(0.5f).add(100, 0));
        textLabel.center = true;
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
        //
        soundPlayer = new SoundPlayer();
        soundPlayer.stopWhenDispose = false;
        addChild(soundPlayer);
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
        winSprite.visible = true;
        textCount.start();
        soundPlayer.play("READY");
    }

    protected void text_out() {
        textLabel.visible = false;
        winSprite.visible = false;
        emit_signal("text_out");
    }
}

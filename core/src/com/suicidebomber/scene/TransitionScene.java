package com.suicidebomber.scene;

import com.suicidebomber.engine.AnimatedSprite;
import com.suicidebomber.engine.AnimationSprite;
import com.suicidebomber.engine.Canvas2D;

// Signal: disappear_ended

public class TransitionScene extends Canvas2D {

    public AnimatedSprite animatedSprite;

    public void create() {
        super.create();
        animatedSprite = new AnimatedSprite();
        addChild(animatedSprite);

        AnimationSprite appearAnim = new AnimationSprite();
        appearAnim.addSprite("BLACK_SCREEN_1");
        appearAnim.addSprite("BLACK_SCREEN_2");
        appearAnim.addSprite("BLACK_SCREEN_3");
        appearAnim.addSprite("BLACK_SCREEN_4");
        appearAnim.addSprite("BLACK_SCREEN_5");
        appearAnim.addSprite("BLACK_SCREEN_6");
        appearAnim.addSprite("BLACK_SCREEN_7");
        appearAnim.addSprite("BLACK_SCREEN_8");
        appearAnim.addSprite("BLACK_SCREEN_9");
        appearAnim.addSprite("BLACK_SCREEN_10");
        appearAnim.addSprite("BLACK_SCREEN_11");
        appearAnim.looping = false;
        appearAnim.delay = 100;
        animatedSprite.addAnimation("APPEAR", appearAnim);

        AnimationSprite disappearAnim = new AnimationSprite();
        disappearAnim.addSprite("BLACK_SCREEN_11");
        disappearAnim.addSprite("BLACK_SCREEN_10");
        disappearAnim.addSprite("BLACK_SCREEN_9");
        disappearAnim.addSprite("BLACK_SCREEN_8");
        disappearAnim.addSprite("BLACK_SCREEN_7");
        disappearAnim.addSprite("BLACK_SCREEN_6");
        disappearAnim.addSprite("BLACK_SCREEN_5");
        disappearAnim.addSprite("BLACK_SCREEN_4");
        disappearAnim.addSprite("BLACK_SCREEN_3");
        disappearAnim.addSprite("BLACK_SCREEN_2");
        disappearAnim.addSprite("BLACK_SCREEN_1");
        disappearAnim.looping = false;
        disappearAnim.delay = 100;
        animatedSprite.addAnimation("DISAPPEAR", disappearAnim);
        disappearAnim.connect_signal("animation_ended", this, "disappear_ended");
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("disappear_ended")) {
            emit_signal("disappear_ended");
        }
    }

    public void appear() {
        animatedSprite.play("APPEAR");
    }

    public void disappear() {
        animatedSprite.play("DISAPPEAR");
    }
}

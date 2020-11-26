package com.suicidebomber.element;

import com.suicidebomber.engine.*;
import com.suicidebomber.autoload.GameElement;


public class Fire extends MapElement {

    public Player owner = null;
    public Timing timer;

    private AnimatedSprite sprite;

    public Fire() {
        super();
        sprite = new AnimatedSprite();
        renderElement.add(sprite);
        addChild(sprite);

        AnimationSprite fireAnim = new AnimationSprite();
        fireAnim.addSprite("FIRE_1");
        fireAnim.addSprite("FIRE_2");
        fireAnim.addSprite("FIRE_3");
        fireAnim.addSprite("FIRE_4");
        fireAnim.addSprite("FIRE_5");
        fireAnim.addSprite("FIRE_6");
        fireAnim.delay = 70;
        fireAnim.looping = false;
        sprite.addAnimation("FIRE", fireAnim);
        sprite.play("FIRE");

        timer = new Timing();
        timer.wait_time = GameElement.fireTiming;
        timer.connect_signal("time_out", this, "runoff");
        timer.start();
        addChild(timer);
        blockType = GameElement.BlockType.FIRE;
        initScore = -1.0f;
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("runoff")) {
            currentMap.getMapBlock(currentBlock).blockType = GameElement.BlockType.GRASS;
            safefree();
        }
    }
}

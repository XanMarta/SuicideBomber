package com.suicidebomber.game;

import com.suicidebomber.GameElement;
import com.suicidebomber.element.MapElement;
import com.suicidebomber.element.Timing;


public class Fire extends MapElement {

    public Player owner = null;
    public Timing timer;

    public Fire() {
        super();
        timer = new Timing();
        timer.wait_time = GameElement.fireTiming;
        timer.connect_signal("time_out", this, "runoff");
        timer.start();
        addChild(timer);
        sprite.image = "FIRE";
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("runoff")) {
            currentMap.getMapBlock(currentBlock).blockType = GameElement.BlockType.GRASS;
            free();
        }
    }
}

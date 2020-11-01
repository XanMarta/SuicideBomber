package com.suicidebomber.element;

import com.suicidebomber.game.GameElement;
import com.suicidebomber.engine.MapElement;
import com.suicidebomber.engine.Timing;


public class Fire extends MapElement {

    public Player owner = null;
    public Timing timer;

    public Fire() {
        super();
        sprite.image = "FIRE";
        blockType = GameElement.BlockType.FIRE;
        timer = new Timing();
        timer.wait_time = GameElement.fireTiming;
        timer.connect_signal("time_out", this, "runoff");
        timer.start();
        addChild(timer);
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("runoff")) {
            currentMap.getMapBlock(currentBlock).blockType = GameElement.BlockType.GRASS;
            free();
        }
    }
}

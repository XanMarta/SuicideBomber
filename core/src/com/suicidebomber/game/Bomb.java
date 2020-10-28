package com.suicidebomber.game;

import com.suicidebomber.GameElement;
import com.suicidebomber.element.MapElement;
import com.suicidebomber.element.Timing;


public class Bomb extends MapElement {

    public Actor owner = null;

    private Timing timer;

    public Bomb() {
        super();
        timer = new Timing();
        timer.wait_time = GameElement.bombTiming;
        timer.connect_signal("time_out", this, "runoff");
        timer.start();
        addChild(timer);
        sprite.image = "IMAGE";
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("runoff")) {
            System.out.println("BOOM");
            currentMap.getMapBlock(currentBlock).blockType = GameElement.BlockType.GRASS;
            free();
        }
    }

}

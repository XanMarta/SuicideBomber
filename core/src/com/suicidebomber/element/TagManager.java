package com.suicidebomber.element;

import com.suicidebomber.engine.Canvas2D;
import com.suicidebomber.engine.Timing;
import java.util.ArrayList;


public class TagManager extends Canvas2D {

    private ArrayList<PlayerTag> tags = new ArrayList<>();
    private Timing updateTimer;

    public void create() {
        for (int i = 3; i >= 0; i--) {
            PlayerTag tag = new PlayerTag();
            tag.position.set(0, 180 * i);
            addChild(tag);
            tags.add(tag);
        }
        updateTimer = new Timing();
        updateTimer.wait_time = 1000;
        updateTimer.looping = true;
        updateTimer.connect_signal("time_out", this, "update");
        addChild(updateTimer);
        updateTimer.start();
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("update")) {
            update();
        }
    }

    public void connectPlayer(Player player) {
        for (PlayerTag tag : tags) {
            if (!tag.hasPlayer) {
                player.tag = tag;
                tag.hasPlayer = true;
                tag.currentPlayer = player;
                tag.update(player);
                return;
            }
        }
    }

    private void update() {
        for (PlayerTag tag : tags) {
            tag.update();
        }
    }

}

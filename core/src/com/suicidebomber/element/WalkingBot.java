package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.engine.Timing;
import com.suicidebomber.structure.GameElement;
import java.util.ArrayList;


public class WalkingBot extends Player {

    protected Timing delayBot;
    protected boolean isDelaying = false;

    public void create() {
        super.create();
        delayBot = new Timing();
        delayBot.wait_time = 0.1f;
        delayBot.connect_signal("time_out", this, "bot_enddelay");
        addChild(delayBot);
    }

    public void render() {
        super.render();
        botMovement();
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("bot_enddelay")) {
            botEndDelay();
        }
    }

    public void botStartDelay() {
        delayBot.start();
        isDelaying = true;
    }

    public void botEndDelay() {
        isDelaying = false;
    }

    public void botMovement() {
        if (!isDelaying) {
            if (direction.isZero()) {
                if (GameElement.random.nextInt(100) < 80) {
                    findWay();
                } else {
                    botStartDelay();
                }
            }
            movePlayer(direction);
        }
    }

    protected void findWay() {
        ArrayList<Vector2> legalWay = new ArrayList<>();
        checkLegitDirection(new Vector2(-1, 0), legalWay);
        checkLegitDirection(new Vector2(1, 0), legalWay);
        checkLegitDirection(new Vector2(0, -1), legalWay);
        checkLegitDirection(new Vector2(0, 1), legalWay);
        if (legalWay.size() > 0) {
            direction.set(legalWay.get(GameElement.random.nextInt(legalWay.size())));
        } else {
            direction.setZero();
        }
    }

    protected void checkLegitDirection(Vector2 direction, ArrayList<Vector2> legalWay) {
        if (isLegitBlock(currentMap.getMapBlock(new Vector2(direction).add(currentBlock)))) {
            legalWay.add(direction);
        }
    }
}

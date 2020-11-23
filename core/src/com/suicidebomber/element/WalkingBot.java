package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.engine.Timing;
import com.suicidebomber.structure.GameElement;
import java.util.ArrayList;


public class WalkingBot extends Bot {

    protected Timing delayBot;
    protected boolean isDelaying = false;

    public void create() {
        super.create();
        delayBot = new Timing();
        delayBot.wait_time = 0.5f;
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
        if (isPlaying) {
            if (!isDelaying) {
                if (direction.isZero()) {
                    findWay();
                } else if (isNearCenter) {
                    if (GameElement.random.nextInt(100) < 10) {
                        findWay();
                    }
                }
            }
            movePlayer(direction);
            if (!direction.isZero()) {
                botStartDelay();
            }
        }
    }

    protected void findWay() {
        ArrayList<Vector2> availableWay = new ArrayList<>();
        availableWay.add(new Vector2(-1, 0));
        availableWay.add(new Vector2(1, 0));
        availableWay.add(new Vector2(0, 1));
        availableWay.add(new Vector2(0, -1));
        availableWay.add(new Vector2(0, 0));
        float maxScore = -2.0f;
        for (Vector2 checkWay : availableWay) {
            float checkScore = currentMap.getScore(new Vector2(checkWay).add(currentBlock));
            if (checkScore > maxScore) {
                maxScore = checkScore;
            }
        }
        ArrayList<Vector2> legitWay = new ArrayList<>();
        for (Vector2 checkWay : availableWay) {
            if (currentMap.getScore(new Vector2(checkWay).add(currentBlock)) == maxScore) {
                legitWay.add(checkWay);
                if (checkWay.epsilonEquals(direction)) {
                    if (GameElement.random.nextInt(100) < 50) {
                        return;
                    }
                }
            }
        }
        direction.set(legitWay.get(GameElement.random.nextInt(legitWay.size())));
    }
}

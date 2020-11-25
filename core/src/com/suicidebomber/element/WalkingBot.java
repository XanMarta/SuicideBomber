package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.source.manager.GameHelper;
import java.util.ArrayList;


public class WalkingBot extends Bot {

    public void botMovement() {
        if (isPlaying) {
            if (direction.isZero()) {
                botFindway();
            }
            movePlayer(direction);
        }
    }

    public void botFindway() {
        ArrayList<Vector2> availableWay = new ArrayList<>();
        availableWay.add(new Vector2(-1, 0));
        availableWay.add(new Vector2(1, 0));
        availableWay.add(new Vector2(0, 1));
        availableWay.add(new Vector2(0, -1));
        ArrayList<Vector2> legitWay = new ArrayList<>();
        for (Vector2 checkWay : availableWay) {
            if (currentMap.getScore(new Vector2(checkWay).add(currentBlock)) != -0.768f) {
                legitWay.add(checkWay);
            }
        }
        if (legitWay.size() > 0) {
            direction.set(legitWay.get(GameHelper.instance().random.nextInt(legitWay.size())));
        }
    }
}

package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.source.manager.GameHelper;


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
        availableWay.clear();
        availableWay.add(new Vector2(-1, 0));
        availableWay.add(new Vector2(1, 0));
        availableWay.add(new Vector2(0, 1));
        availableWay.add(new Vector2(0, -1));
        legitWay.clear();
        for (Vector2 checkWay : availableWay) {
            float score = currentMap.getScore(new Vector2(checkWay).add(currentBlock));
            if (score > -0.5f) {
                legitWay.add(checkWay);
            }
        }
        if (legitWay.size() > 0) {
            direction.set(legitWay.get(GameHelper.instance().random.nextInt(legitWay.size())));
        }
        botDropBomb();
    }
}

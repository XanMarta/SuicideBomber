package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.source.manager.GameHelper;


public class DodgeBot extends Bot {

    public void botMovement() {
        if (isPlaying) {
            float currentScore = currentMap.getScore(new Vector2(currentBlock).add(direction));
            float nextScore = currentMap.getScore(currentBlock);
            if ((currentScore < -0.5f && currentScore != -0.768f)
                    || (nextScore < -0.5f && nextScore != -0.768f)) {
                botFindway();
            } else if (!isDelaying) {
                if (direction.isZero()) {
                    botFindway();
                } else if (isNearCenter) {
                    if (GameHelper.instance().random.nextInt(100) < 10) {
                        botFindway();
                    }
                }
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
        availableWay.add(new Vector2(0, 0));
        float maxScore = -2.0f;
        for (Vector2 checkWay : availableWay) {
            float checkScore = currentMap.getScore(new Vector2(checkWay).add(currentBlock));
            if (checkScore > maxScore) {
                maxScore = checkScore;
            }
        }
        legitWay.clear();
        for (Vector2 checkWay : availableWay) {
            if (currentMap.getScore(new Vector2(checkWay).add(currentBlock)) == maxScore) {
                legitWay.add(checkWay);
            }
        }
        if (legitWay.size() > 0) {
            direction.set(legitWay.get(GameHelper.instance().random.nextInt(legitWay.size())));
        } else {
            direction.setZero();
            botStartDelay(700);
        }
        botDropBomb();
    }
}

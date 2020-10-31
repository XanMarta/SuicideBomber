package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.game.GameElement;
import com.suicidebomber.engine.MapBlock;
import com.suicidebomber.engine.MapElement;


public class Actor extends MapElement {

    public float speed = GameElement.defaultSpeed;

    public boolean isLegitBlock(MapBlock block) {
        return (block.blockType == GameElement.BlockType.GRASS);
    }

    public void moveActor(Vector2 direction) {
        if (!direction.isZero()) {
            Vector2 velocity = new Vector2(direction).scl(speed);
            Vector2 toCurrent = new Vector2(currentMap.blockToPosition(currentBlock)).sub(position);
            Vector2 directToCurrent = new Vector2(toCurrent).nor();
            MapBlock nextBlock = currentMap.blockAt(new Vector2(direction).add(currentBlock));

            if (directToCurrent.isZero()) {                                    // In center
                if (!isLegitBlock(nextBlock)) {
                    velocity.setZero();
                }
            } else if (direction.hasSameDirection(directToCurrent)) {          // To current direction and current line
                if (!isLegitBlock(nextBlock)) {
                    if (velocity.len() > toCurrent.len()) {
                        velocity.set(toCurrent);
                    }
                }
            } else if (direction.hasOppositeDirection(directToCurrent)) {      // To opposite direction and current line
                if (!isLegitBlock(nextBlock)) {
                    velocity.setZero();
                }
            } else {                                                           // To opposite line
                if (isLegitBlock(nextBlock)) {
                    if (toCurrent.len() <= GameElement.autoMargin) {
                        velocity.set(toCurrent);
                    } else if (toCurrent.len() <= GameElement.changeDirectionMargin) {
                        float temp = velocity.len();
                        velocity.set(directToCurrent).scl(temp);
                    } else {
                        Vector2 toNext = currentMap.blockToPosition(new Vector2(directToCurrent).scl(-1)
                                .add(currentBlock)).sub(position);
                        if (toNext.len() <= GameElement.changeDirectionMargin) {
                            float temp = velocity.len();
                            velocity.set(toNext.nor()).scl(temp);
                        } else if (toNext.len() <= GameElement.autoMargin) {
                            velocity.set(toNext);
                        } else {
                            velocity.setZero();
                        }
                    }
                } else {
                    velocity.setZero();
                }
            }

            position.add(velocity);
            updateBlock();
        }
    }
}

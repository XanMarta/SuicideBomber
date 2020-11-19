package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.structure.GameElement;
import com.suicidebomber.engine.MapBlock;
import com.suicidebomber.engine.MapElement;


public class Actor extends MapElement { // Movement element

    public float moveSpeed = GameElement.defaultSpeed;

    public boolean isLegitBlock(MapBlock block) {
        return (block.blockType == GameElement.BlockType.GRASS ||
                block.blockType == GameElement.BlockType.ITEM ||
                block.blockType == GameElement.BlockType.FIRE);
    }

    public Vector2 moveActor(Vector2 direction) {
        if (!direction.isZero()) {
            Vector2 velocity = new Vector2(direction).scl(moveSpeed);
            Vector2 toCenter = new Vector2(currentMap.blockToCenter(currentBlock)).sub(center);
            Vector2 directToCenter = new Vector2(toCenter).nor();
            MapBlock nextBlock = currentMap.blockAt(new Vector2(direction).add(currentBlock));

            if (directToCenter.isZero()) {
                if (!isLegitBlock(nextBlock)) {
                    velocity.setZero();
                }
            } else if (direction.hasSameDirection(directToCenter)) {
                if (!isLegitBlock(nextBlock)) {
                    if (velocity.len() > toCenter.len()) {
                        velocity.set(toCenter);
                    }
                }
            } else if (direction.hasOppositeDirection(directToCenter)) {
                if (!isLegitBlock(nextBlock)) {
                    velocity.setZero();
                }
            } else {
                if (!isLegitBlock(nextBlock)) {
                    velocity.setZero();
                } else {
                    if (toCenter.len() <= GameElement.autoMargin) {
                        velocity.set(toCenter);
                    } else if (toCenter.len() <= GameElement.changeDirectionMargin) {
                        if (velocity.len() > toCenter.len()) {
                            velocity.set(toCenter);
                        } else {
                            Vector2 temp = new Vector2(directToCenter).scl(velocity.len());
                            velocity.set(temp);
                        }
                    } else {
                        velocity.setZero();
                    }
                }
            }

            center.add(velocity);
            updatePosition();
            return new Vector2(velocity).nor();
        }
        return new Vector2(0, 0);
    }
}


package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.autoload.GameElement;
import com.suicidebomber.engine.MapBlock;
import com.suicidebomber.engine.MapElement;

// Movement element
public class Actor extends MapElement {

    protected float moveSpeed = GameElement.defaultSpeed;
    protected Vector2 nearbyBlock = new Vector2(-1, -1);
    protected Vector2 center = new Vector2(0, 0);
    protected boolean isNearCenter = false;

    protected boolean isLegitBlock(Vector2 block) {    // Check if block can go through
        MapBlock blockType = currentMap.getMapBlock(block);
        return (blockType.blockType == GameElement.BlockType.GRASS ||
                blockType.blockType == GameElement.BlockType.ITEM ||
                blockType.blockType == GameElement.BlockType.FIRE);
    }

    protected Vector2 moveActor(Vector2 direction) {
        if (!direction.isZero()) {
            Vector2 velocity = new Vector2(direction).scl(moveSpeed);
            Vector2 toCenter = new Vector2(currentMap.blockToCenter(currentBlock)).sub(center);
            Vector2 directToCenter = new Vector2(toCenter).nor();
            Vector2 nextBlock = new Vector2(direction).add(currentBlock);

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
                    if (toCenter.len() <= GameElement.centerMargin) {
                        velocity.set(toCenter);
                    } else if (toCenter.len() <= GameElement.nearCenterMargin) {
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

    protected void updatePosition() {                              // Need calling after center change
        position.set(center).sub(currentMap.centerOffset);
        currentBlock.set(currentMap.positionToBlock(center));
        Vector2 exactCen = currentMap.blockToCenter(currentBlock);
        Vector2 exactMargin = new Vector2(center).sub(exactCen);
        if (exactCen.epsilonEquals(center, 0.1f)) {
            nearbyBlock.set(-1, -1);
        } else {
            nearbyBlock.set(new Vector2(exactMargin).nor().add(currentBlock));
        }
        isNearCenter = (exactMargin.len() < GameElement.nearCenterMargin);
    }

    public void setBlock(Vector2 block) {
        super.setBlock(block);
        center.set(currentMap.blockToCenter(block));
        updatePosition();
    }
}


package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.game.GameElement;
import com.suicidebomber.engine.MapBlock;
import com.suicidebomber.engine.MapElement;


public class Actor extends MapElement { // Movement element

    public float moveSpeed = GameElement.defaultSpeed;

    public boolean isLegitBlock(MapBlock block) {
        return (block.blockType == GameElement.BlockType.GRASS ||
                block.blockType == GameElement.BlockType.ITEM ||
                block.blockType == GameElement.BlockType.FIRE);
    }

    public void moveActorv(Vector2 direction) {
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
        }
    }

//    public void moveActor(Vector2 direction) {
//        if (!direction.isZero()) {
//            Vector2 velocity = new Vector2(direction).scl(moveSpeed);
//            Vector2 toCurrent = new Vector2(currentMap.blockToPosition(currentBlock)).sub(position);
//            Vector2 directToCurrent = new Vector2(toCurrent).nor();
//            MapBlock nextBlock = currentMap.blockAt(new Vector2(direction).add(currentBlock));
//
//            if (directToCurrent.isZero()) {                                    // In center
//                if (!isLegitBlock(nextBlock)) {
//                    velocity.setZero();
//                }
//            } else if (direction.hasSameDirection(directToCurrent)) {          // To current direction and current line
//                if (!isLegitBlock(nextBlock)) {
//                    if (velocity.len() > toCurrent.len()) {
//                        velocity.set(toCurrent);
//                    }
//                }
//            } else if (direction.hasOppositeDirection(directToCurrent)) {      // To opposite direction and current line
//                if (!isLegitBlock(nextBlock)) {
//                    velocity.setZero();
//                }
//            } else {                                                           // To opposite line
//                if (isLegitBlock(nextBlock)) {
//                    if (toCurrent.len() <= GameElement.autoMargin) {
//                        velocity.set(toCurrent);
//                    } else if (toCurrent.len() <= GameElement.changeDirectionMargin) {
//                        float temp = velocity.len();
//                        velocity.set(directToCurrent).scl(temp);
//                    } else {
//                        Vector2 toNext = currentMap.blockToPosition(new Vector2(directToCurrent).scl(-1)
//                                .add(currentBlock)).sub(position);
//                        if (toNext.len() <= GameElement.changeDirectionMargin) {
//                            float temp = velocity.len();
//                            velocity.set(toNext.nor()).scl(temp);
//                        } else if (toNext.len() <= GameElement.autoMargin) {
//                            velocity.set(toNext);
//                        } else {
//                            velocity.setZero();
//                        }
//                    }
//                } else {
//                    velocity.setZero();
//                }
//            }
//
//            position.add(velocity);
//            updateBlock();
//        }
//    }
//
//    public void updateBlock() {
//        currentBlock.set(currentMap.positionToBlock(position));
//        Vector2 exactPos = currentMap.blockToPosition(currentBlock);
//        if (position.epsilonEquals(exactPos, 0.05f)) {
//            nearbyBlock.set(-1, -1);
//        } else {
//            nearbyBlock.set(new Vector2(position).sub(exactPos).nor().add(currentBlock));
//        }
//    }
}

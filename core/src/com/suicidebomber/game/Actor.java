package com.suicidebomber.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.GameElement;
import com.suicidebomber.element.MapBlock;
import com.suicidebomber.element.MapElement;


public class Actor extends MapElement {

    public float speed = GameElement.defaultSpeed;

    public Actor() {
        super();
    }

    public void render() {
        super.render();
        playerMovement();
    }

    public void playerMovement() {
        Vector2 direction = new Vector2().setZero();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            direction.x = -1;
            direction.y = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            direction.x = 1;
            direction.y = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            direction.y = 1;
            direction.x = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            direction.y = -1;
            direction.x = 0;
        }
        moveActor(direction);
    }

    public void moveActor(Vector2 direction) {
        if (!direction.isZero()) {
            Vector2 velocity = new Vector2(direction).scl(speed);
            Vector2 toCurrent = new Vector2(currentMap.blockToPosition(currentBlock)).sub(position);
            Vector2 directToCurrent = new Vector2(toCurrent).nor();
            MapBlock nextBlock = currentMap.blockAt(new Vector2(direction).add(currentBlock));

            if (directToCurrent.isZero()) {                                     // In center
                if (nextBlock.blockType != GameElement.BlockType.GRASS) {
                    velocity.setZero();
                }
            } else if (direction.hasSameDirection(directToCurrent)) {          // To current direction and current line
                if (nextBlock.blockType != GameElement.BlockType.GRASS) {
                    if (velocity.len() > toCurrent.len()) {
                        velocity.set(toCurrent);
                    }
                }
            } else if (direction.hasOppositeDirection(directToCurrent)) {      // To opposite direction and current line
                if (nextBlock.blockType != GameElement.BlockType.GRASS) {
                    velocity.setZero();
                    position.set(currentMap.blockToPosition(currentBlock));
                }
            } else {                                                           // To opposite line
                if (nextBlock.blockType == GameElement.BlockType.GRASS) {
                    if (toCurrent.len() <= GameElement.autoMargin) {
                        velocity.set(toCurrent);
                    } else if (toCurrent.len() <= GameElement.changeDirectionMargin) {
                        float temp = velocity.len();
                        velocity.set(directToCurrent).scl(temp);
                    } else {
                        Vector2 toNext = currentMap.blockToPosition(new Vector2(directToCurrent).scl(-1).add(currentBlock))
                                .sub(position);
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

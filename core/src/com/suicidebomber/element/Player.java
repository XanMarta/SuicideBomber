package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.engine.MapElement;
import com.suicidebomber.engine.Timing;
import com.suicidebomber.game.GameElement;


public class Player extends Actor {     // Of course this is Player

    public int bomb = 0;
    public int heart = 0;
    public int power = 0;
    public int speed = 0;
    public Vector2 defaultBlock = new Vector2(0, 0);

    public int used_bomb = 0;
    public PlayerTag tag = null;
    public boolean isLiving = false;
    public Timing deadTimer;

    public void create() {
        super.create();
        bomb = GameElement.init_bomb;
        heart = GameElement.init_heart;
        power = GameElement.init_power;
        speed = GameElement.init_speed;
        updateElement();
        deadTimer = new Timing();
        deadTimer.wait_time = 3.0f;
        deadTimer.connect_signal("time_out", this, "deadtimer_time_out");
        addChild(deadTimer);
        playerSpawn();
    }

    public void dispose() {
        super.dispose();
        if (tag != null) {
            tag.hasPlayer = false;
            tag.update(null);
        }
    }

    public void playerSpawn() {
        elementVisible = true;
        isLiving = true;
        setBlock(defaultBlock);
    }

    public void playerDie() {
        elementVisible = false;
        isLiving = false;
        deadTimer.start();
    }

    public void updateElement() {
        moveSpeed = GameElement.defaultSpeed * (1 + speed / 10.0f);
        if (tag != null) {
            tag.update(this);
        }
    }

    public void movePlayer(Vector2 direction) {
        moveActor(direction);
        checkCollision(currentBlock);
        if (isLiving) {
            if (nearbyBlock.x >= 0 && nearbyBlock.y >= 0) {
                checkCollision(nearbyBlock);
            }
        }
    }

    public void dropBomb() {
        if (used_bomb < bomb) {
            if (currentMap.getMapBlock(currentBlock).blockType == GameElement.BlockType.GRASS) {
                Bomb bomb = new Bomb();
                bomb.owner = this;
                bomb.power = this.power + 1;
                bomb.setMap(currentMap);
                bomb.setBlock(currentBlock);
                currentMap.getChild("bomb").addChild(bomb);
                used_bomb += 1;
            }
        }
    }

    public void checkCollision(Vector2 pos) {
        if (currentMap.getMapBlock(pos).blockType == GameElement.BlockType.ITEM) {
            for (MapElement element : currentMap.getMapBlock(pos).elements) {
                if (element instanceof Item) {
                    Item item = (Item) element;
                    switch (item.item_name) {
                        case "BOMB":
                            if (bomb < GameElement.max_element) {
                                bomb += 1;
                            }
                            break;
                        case "HEART":
                            if (heart < GameElement.max_element) {
                                heart += 1;
                            }
                            break;
                        case "POWER":
                            if (power < GameElement.max_element) {
                                power += 1;
                            }
                            break;
                        case "SPEED":
                            if (speed < GameElement.max_element) {
                                speed += 1;
                            }
                            break;
                    }
                    item.disappear();
                    updateElement();
                }
            }
        } else if (currentMap.getMapBlock(pos).blockType == GameElement.BlockType.FIRE) {
            heart -= 1;
            updateElement();
            playerDie();
        }
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("deadtimer_time_out")) {
            if (heart > 0) {
                playerSpawn();
            } else {
//                safefree();
            }
        }
    }
}

package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.engine.MapElement;
import com.suicidebomber.game.GameElement;


public class Player extends Actor {     // Of course this is Player

    public int bomb;
    public int heart;
    public int power;
    public int speed;

    public int current_bomb;

    public Player() {
        super();
        bomb = GameElement.init_bomb;
        heart = GameElement.init_heart;
        power = GameElement.init_power;
        speed = GameElement.init_speed;
        current_bomb = bomb;
    }

    public void movePlayer(Vector2 direction) {
        moveActor(direction);
        checkCollision(currentBlock);
        if (nearbyBlock.x >= 0 && nearbyBlock.y >= 0) {
            checkCollision(nearbyBlock);
        }
    }

    public void dropBomb() {
        if (current_bomb > 0) {
            if (currentMap.getMapBlock(currentBlock).blockType == GameElement.BlockType.GRASS) {
                Bomb bomb = new Bomb();
                bomb.owner = this;
                bomb.power = this.power;
                bomb.setMap(currentMap, currentBlock);
                currentMap.getChild("bomb").addChild(bomb);
                current_bomb -= 1;
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
                            bomb += 1;
                            current_bomb += 1;
                            break;
                        case "HEART":
                            heart += 1;
                            break;
                        case "POWER":
                            power += 1;
                            break;
                        case "SPEED":
                            speed += 1;
                            break;
                    }
                    item.disappear();
                }
            }
        }
    }

}

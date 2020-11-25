package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.engine.AnimatedSprite;
import com.suicidebomber.engine.AnimationSprite;
import com.suicidebomber.engine.MapElement;
import com.suicidebomber.engine.Timing;
import com.suicidebomber.structure.GameElement;

// Signal: player_die

public class Player extends Actor {     // Of course this is Player

    public int bomb = 0;
    public int heart = 0;
    public int power = 0;
    public int speed = 0;
    public Vector2 defaultBlock = new Vector2(0, 0);
    public String color = "BLUE";
    public String playerName = "";

    public int used_bomb = 0;
    public PlayerTag tag = null;
    public boolean isLiving = true;
    public boolean isPlaying = false;
    public Timing deadTimer;

    public AnimatedSprite animatedSprite;
    public Vector2 direction = new Vector2(0, 0);


    public void create() {
        super.create();
        bomb = GameElement.init_bomb;
        heart = GameElement.init_heart;
        power = GameElement.init_power;
        speed = GameElement.init_speed;
        updateElement();
        deadTimer = new Timing();
        deadTimer.wait_time = 3000;
        deadTimer.connect_signal("time_out", this, "deadtimer_time_out");
        addChild(deadTimer);
        isLiving = true;
        generateAnimation();
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
        isPlaying = true;
        setBlock(defaultBlock);
    }

    public void playerDie() {
        elementVisible = false;
        isPlaying = false;
        deadTimer.start();
    }

    public void updateElement() {
        moveSpeed = GameElement.defaultSpeed * (1 + speed / 10.0f);
        if (tag != null) {
            tag.update(this);
        }
    }

    public void movePlayer(Vector2 direction) {
        if (direction.isZero()) {
            animatedSprite.pause();
        } else if (direction.epsilonEquals(0, 1)) {
            animatedSprite.play("UP");
        } else if (direction.epsilonEquals(1, 0)) {
            animatedSprite.play("LEFT");
        } else if (direction.epsilonEquals(-1, 0)) {
            animatedSprite.play("RIGHT");
        } else {
            animatedSprite.play("DOWN");
        }
        direction.set(moveActor(direction));
        checkCollision(currentBlock);
        if (isPlaying) {
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
                isLiving = false;
                emit_signal("player_die");
            }
        }
    }

    private void generateAnimation() {
        animatedSprite = new AnimatedSprite();
        renderElement.add(animatedSprite);
        addChild(animatedSprite);

        AnimationSprite upAnim = new AnimationSprite();
        upAnim.addSprite(color + "_UP_1");
        upAnim.addSprite(color + "_UP_2");
        upAnim.addSprite(color + "_UP_3");
        upAnim.addSprite(color + "_UP_4");
        upAnim.addSprite(color + "_UP_5");
        upAnim.delay = 100;
        animatedSprite.addAnimation("UP", upAnim);

        AnimationSprite leftAnim = new AnimationSprite();
        leftAnim.addSprite(color + "_LEFT_1");
        leftAnim.addSprite(color + "_LEFT_2");
        leftAnim.addSprite(color + "_LEFT_3");
        leftAnim.addSprite(color + "_LEFT_4");
        leftAnim.addSprite(color + "_LEFT_5");
        leftAnim.delay = 100;
        animatedSprite.addAnimation("LEFT", leftAnim);

        AnimationSprite rightAnim = new AnimationSprite();
        rightAnim.addSprite(color + "_RIGHT_1");
        rightAnim.addSprite(color + "_RIGHT_2");
        rightAnim.addSprite(color + "_RIGHT_3");
        rightAnim.addSprite(color + "_RIGHT_4");
        rightAnim.addSprite(color + "_RIGHT_5");
        rightAnim.delay = 100;
        animatedSprite.addAnimation("RIGHT", rightAnim);

        AnimationSprite downAnim = new AnimationSprite();
        downAnim.addSprite(color + "_DOWN_1");
        downAnim.addSprite(color + "_DOWN_2");
        downAnim.addSprite(color + "_DOWN_3");
        downAnim.addSprite(color + "_DOWN_4");
        downAnim.addSprite(color + "_DOWN_5");
        downAnim.delay = 100;
        animatedSprite.addAnimation("DOWN", downAnim);

        AnimationSprite normalAnim = new AnimationSprite();
        normalAnim.addSprite(color + "_NORMAL");

        animatedSprite.addAnimation("NORMAL", normalAnim);
        animatedSprite.play("NORMAL");
    }
}

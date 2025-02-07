package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.engine.*;
import com.suicidebomber.autoload.GameElement;


public class Bomb extends MapElement {

    public Player owner = null;
    public int power = 3;

    private Timing timer;
    private AnimatedSprite sprite;
    private SoundPlayer soundPlayer;

    public Bomb() {
        super();
        sprite = new AnimatedSprite();
        AnimationSprite bombAnim = new AnimationSprite();
        bombAnim.addSprite("BOMB_1");
        bombAnim.addSprite("BOMB_2");
        bombAnim.addSprite("BOMB_3");
        bombAnim.addSprite("BOMB_4");
        bombAnim.addSprite("BOMB_5");
        bombAnim.addSprite("BOMB_6");
        bombAnim.delay = 500;
        bombAnim.looping = false;
        sprite.addAnimation("BOMB", bombAnim);
        renderElement.add(sprite);
        sprite.play("BOMB");
        addChild(sprite);

        timer = new Timing();
        timer.wait_time = GameElement.bombTiming;
        timer.connect_signal("time_out", this, "runoff");
        timer.start();
        addChild(timer);

        soundPlayer = new SoundPlayer();
        soundPlayer.play("BOMB_TIMER");
        soundPlayer.stopWhenDispose = false;
        addChild(soundPlayer);

        blockType = GameElement.BlockType.BOMB;
        initScore = -1.0f;
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("runoff")) {
            runoff();
        }
    }

    public void disappear() {
        super.disappear();
        timer.stop();
        timer.wait_time = GameElement.delayBombSpreadTime;
        timer.start();
    }

    public void runoff() {
        timer.stop();
        currentMap.getMapBlock(currentBlock).blockType = GameElement.BlockType.GRASS;
        if (owner != null) {
            owner.used_bomb -= 1;
        }
        boom(new Vector2(currentBlock), new Vector2(0, 0), power);
        soundPlayer.stop();
        soundPlayer.play("BOOM");
        safefree();
    }

    public void specialScore() {
        super.specialScore();
        checkSpreadScore(new Vector2(currentBlock).add(-1, 0), new Vector2(-1, 0), power);
        checkSpreadScore(new Vector2(currentBlock).add(1, 0), new Vector2(1, 0), power);
        checkSpreadScore(new Vector2(currentBlock).add(0, -1), new Vector2(0, -1), power);
        checkSpreadScore(new Vector2(currentBlock).add(0, 1), new Vector2(0, 1), power);
    }

    private void checkSpreadScore(Vector2 pos, Vector2 dir, int pow) {
        if (currentMap.isInMap(pos) && pow > 1) {
            if (currentMap.getMapBlock(pos).blockType == GameElement.BlockType.GRASS) {
                currentMap.setScore(pos, -0.7f);
                checkSpreadScore(new Vector2(pos).add(dir), dir, pow - 1);
            }
        }
    }

    public void boom(Vector2 pos, Vector2 direction, int power) {
        if (currentMap.getMapBlock(pos).blockType == GameElement.BlockType.GRASS) {
            Fire fire = new Fire();
            fire.owner = owner;
            fire.setMap(currentMap);
            fire.setBlock(pos);
            currentMap.getChild("fire").addChild(fire);
            // Spread boom
            if (power > 1) {
                if (direction.isZero()) {
                    boom(new Vector2(pos.x, pos.y + 1), new Vector2(0, 1), power - 1);
                    boom(new Vector2(pos.x, pos.y - 1), new Vector2(0, -1), power - 1);
                    boom(new Vector2(pos.x + 1, pos.y), new Vector2(1, 0), power - 1);
                    boom(new Vector2(pos.x - 1, pos.y), new Vector2(-1, 0), power - 1);
                } else {
                    boom(new Vector2(pos).add(direction), new Vector2(direction), power - 1);
                }
            }
        } else if (currentMap.getMapBlock(pos).blockType == GameElement.BlockType.BOMB) {
            for (MapElement element : currentMap.getMapBlock(pos).elements) {
                if (element instanceof Bomb) {
                    element.disappear();
                }
            }
        } else if (currentMap.getMapBlock(pos).blockType == GameElement.BlockType.BOX) {
            for (MapElement element : currentMap.getMapBlock(pos).elements) {
                if (element instanceof Box) {
                    element.disappear();
                }
            }
        } else if (currentMap.getMapBlock(pos).blockType == GameElement.BlockType.ITEM) {
            for (MapElement element : currentMap.getMapBlock(pos).elements) {
                if (element instanceof Item) {
                    element.disappear();
                }
            }
        }
    }

}

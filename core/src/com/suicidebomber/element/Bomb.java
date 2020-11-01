package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.game.GameElement;
import com.suicidebomber.engine.MapElement;
import com.suicidebomber.engine.Timing;


public class Bomb extends MapElement {

    public Player owner = null;
    public int power = 3;

    private Timing timer;

    public Bomb() {
        super();
        sprite.image = "BOMB";
        blockType = GameElement.BlockType.BOMB;
        timer = new Timing();
        timer.wait_time = GameElement.bombTiming;
        timer.connect_signal("time_out", this, "runoff");
        timer.start();
        addChild(timer);
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("runoff")) {
            System.out.println("BOOM");
            runoff(0);
        }
    }

    public void runoff(float delay) {
        if (delay == 0) {
            timer.stop();
            currentMap.getMapBlock(currentBlock).blockType = GameElement.BlockType.GRASS;
            boom(new Vector2(currentBlock), new Vector2(0, 0), power);
            free();
        } else {
            timer.stop();
            timer.wait_time = delay;
            timer.start();
        }
    }

    public void boom(Vector2 pos, Vector2 direction, int power) {
        if (currentMap.getMapBlock(pos).blockType == GameElement.BlockType.GRASS ||
                currentMap.getMapBlock(pos).blockType == GameElement.BlockType.FIRE) {
            Fire fire = new Fire();
            fire.owner = owner;
            fire.setMap(currentMap, pos);
            currentMap.getChild("fire").addChild(fire);

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
                    ((Bomb) element).runoff(0.05f);
                }
            }
        } else if (currentMap.getMapBlock(pos).blockType == GameElement.BlockType.BOX) {
            for (MapElement element : currentMap.getMapBlock(pos).elements) {
                if (element instanceof Box) {
                    ((Box) element).runoff();
                }
            }
        }
    }

}

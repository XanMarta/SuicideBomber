package com.suicidebomber.element;

import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.autoload.GameElement;
import com.suicidebomber.engine.AnimatedSprite;
import com.suicidebomber.engine.AnimationSprite;
import com.suicidebomber.engine.MapElement;
import com.suicidebomber.engine.Timing;
import com.suicidebomber.source.manager.GameHelper;
import java.util.ArrayList;


public class Portal extends MapElement {

    private AnimatedSprite sprite;
    private Timing portalTimer;

    public void create() {
        super.create();
        sprite = new AnimatedSprite();
        addChild(sprite);

        AnimationSprite spinAnim = new AnimationSprite();
        spinAnim.addSprite("PORTAL_1");
        spinAnim.addSprite("PORTAL_2");
        spinAnim.addSprite("PORTAL_3");
        spinAnim.addSprite("PORTAL_4");
        spinAnim.delay = 200;
        sprite.addAnimation("SPIN", spinAnim);
        sprite.play("SPIN");

        portalTimer = new Timing();
        portalTimer.wait_time = 20000;
        portalTimer.looping = true;
        portalTimer.connect_signal("time_out", this, "portal_out");
        addChild(portalTimer);
        portalTimer.start();
        blockType = GameElement.BlockType.PORTAL;
        setRandomBlock();
    }

    public void renderImage() {
        super.renderImage();
        sprite.renderImage();
    }

    public void setRandomBlock() {
        if (currentMap.getMapBlock(currentBlock).blockType == GameElement.BlockType.PORTAL) {
            currentMap.getMapBlock(currentBlock).blockType = GameElement.BlockType.GRASS;
        }
        setBlock(getRandomBlock());
        portalTimer.wait_time = GameHelper.instance().random.nextInt(10000) + 15000;
    }

    public Vector2 getRandomBlock() {
        ArrayList<Vector2> list = currentMap.getMapBlockList(GameElement.BlockType.GRASS);
        if (list.size() > 0) {
            return new Vector2(list.get(GameHelper.instance().random.nextInt(list.size())));
        }
        return new Vector2(1, 1);
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("portal_out")) {
            setRandomBlock();
        }
    }
}

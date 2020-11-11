package com.suicidebomber.element;

import com.suicidebomber.engine.Canvas2D;
import java.util.ArrayList;


public class TagManager extends Canvas2D {

    public ArrayList<PlayerTag> tags = new ArrayList<>();

    public void create() {
        for (int i = 3; i >= 0; i--) {
            PlayerTag tag = new PlayerTag();
            tag.position.set(0, 180 * i);
            addChild(tag);
            tags.add(tag);
        }
    }

    public void connectPlayer(Player player) {
        for (PlayerTag tag : tags) {
            if (!tag.hasPlayer) {
                player.tag = tag;
                tag.hasPlayer = true;
                tag.update(player);
                return;
            }
        }
    }

}

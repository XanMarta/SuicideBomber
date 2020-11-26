package com.suicidebomber.element;

import com.suicidebomber.source.manager.GameHelper;


public class BomberBot extends WalkingBot {

    public void botDropBomb() {
        if (legitWay.size() == 1) {
            dropBomb();
        } else if (legitWay.size() == 2) {
            if (GameHelper.instance().random.nextInt(100) < 10) {
                dropBomb();
            }
        }
    }
}

package com.suicidebomber;

import com.suicidebomber.element.MapElement;
import com.suicidebomber.element.Node;
import com.suicidebomber.element.Sprite;
import com.suicidebomber.element.TileMap;


public class Testing {

    public Node root;

    public Testing() {
        root = new Node();

        Sprite temp1 = new Sprite();
        temp1.image = "IMAGE";
        root.addChild(temp1);

        TileMap mapPlay = new TileMap();
        GameElement.BlockType[][] tempMap = new GameElement.BlockType[12][12];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                if (i == 0 || j == 0 || i == 11 || j == 11) {
                    tempMap[i][j] = GameElement.BlockType.WALL;
                } else {
                    tempMap[i][j] = GameElement.BlockType.GRASS;
                }
            }
        }
        mapPlay.blockSize = GameElement.blockSize;
        mapPlay.position.set(GameElement.mapPosition);
        mapPlay.generateMap(tempMap, 12, 12);
        root.addChild(mapPlay);

        MapElement player = new MapElement();
        player.sprite.image = "PLAYER";
        player.setCurrentMap(mapPlay);
        player.setBlock(3, 2);
        mapPlay.addChild(player);
    }

}

package com.suicidebomber;

import com.suicidebomber.element.Node;
import com.suicidebomber.element.Node2D;
import com.suicidebomber.element.TileMap;
import com.suicidebomber.game.Player;


public class Testing {

    public Node root;
    public Node house;

    public Testing() {
        root = new Node();

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

        Node2D actor = new Node2D();
        mapPlay.addChild(actor);

        Player player = new Player();
        player.sprite.image = "PLAYER";
        player.currentMap = mapPlay;
        player.setBlock(3, 2);
        actor.addChild(player);
    }

}

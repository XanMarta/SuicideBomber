package com.suicidebomber;

import com.suicidebomber.element.Node;
import com.suicidebomber.element.Node2D;
import com.suicidebomber.element.TileMap;
import com.suicidebomber.game.Player;


public class PlayGround {

    public Node root;
    public Node2D actor;
    public Node2D bomb;
    public Node2D fire;

    public PlayGround() {
        root = new Node();
        root.name = "root";

        TileMap mapPlay = new TileMap();
        mapPlay.name = "mapplay";
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

        actor = new Node2D();
        actor.name = "actor";
        mapPlay.addChild(actor);

        Player player = new Player();
        player.name = "player";
        player.sprite.image = "PLAYER";
        player.currentMap = mapPlay;
        player.setBlock(3, 2);
        actor.addChild(player);

        bomb = new Node2D();
        bomb.name = "bomb";
        mapPlay.addChild(bomb);

        fire = new Node2D();
        fire.name = "fire";
        mapPlay.addChild(fire);
    }

}

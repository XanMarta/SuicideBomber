package com.suicidebomber.game;

import com.suicidebomber.engine.Node;
import com.suicidebomber.engine.Node2D;
import com.suicidebomber.engine.TileMap;
import com.suicidebomber.element.Box;
import com.suicidebomber.element.Player;
import com.suicidebomber.source.MapLoader;


public class PlayGround {

    public Node root;
    public Node2D actor;
    public Node2D bomb;
    public Node2D fire;
    public Node2D box;

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

        bomb = new Node2D();
        bomb.name = "bomb";
        mapPlay.addChild(bomb);

        fire = new Node2D();
        fire.name = "fire";
        mapPlay.addChild(fire);

        box = new Node2D();
        box.name = "box";
        mapPlay.addChild(box);

        for (int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {
                if (i == 5 || j == 5) {
                    if (i == j) {
                        continue;
                    }
                    Box newBox = new Box();
                    newBox.currentMap = mapPlay;
                    newBox.setBlock(i, j);
                    box.addChild(newBox);
                    mapPlay.getMapBlock(newBox.currentBlock).blockType = GameElement.BlockType.BOX;
                }
            }
        }

        Player player = new Player();
        player.name = "player";
        player.sprite.image = "PLAYER";
        player.currentMap = mapPlay;
        player.setBlock(3, 2);
        actor.addChild(player);

        new MapLoader();
    }

}

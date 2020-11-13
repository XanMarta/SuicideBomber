package com.suicidebomber.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.element.*;
import com.suicidebomber.engine.*;


public class PlayGround {

    public Node root;
    public Node2D actor;
    public Node2D bomb;
    public Node2D fire;
    public Node2D box;
    public Node2D wall;
    public Node2D item;

    public PlayGround() {
        root = new Node();
        root.name = "root";

        TileMap mapPlay = new TileMap();
        mapPlay.blockSize.set(GameElement.blockSize);
        mapPlay.name = "mapplay";
        mapPlay.generateMap(GameElement.mapLoader.loadMap("SANDSTORM"));
        mapPlay.position.set(GameElement.mapPosition);
        root.addChild(mapPlay);

        bomb = new Node2D();
        bomb.name = "bomb";
        mapPlay.addChild(bomb);

        fire = new Node2D();
        fire.name = "fire";
        mapPlay.addChild(fire);

        box = new Node2D();
        box.name = "box";
        mapPlay.addChild(box);

        wall = new Node2D();
        wall.name = "wall";
        mapPlay.addChild(wall);

        item = new Node2D();
        item.name = "item";
        mapPlay.addChild(item);

        actor = new Node2D();
        actor.name = "actor";
        mapPlay.addChild(actor);

        for (int i = 0; i < mapPlay.mapSize.x; i++) {
            for (int j = 0; j < mapPlay.mapSize.y; j++) {
                Vector2 pos = new Vector2(i, j);
                GameElement.BlockType type = mapPlay.getMapBlock(pos).blockType;
                if (type == GameElement.BlockType.WALL) {
                    Wall temp = new Wall();
                    temp.setMap(mapPlay);
                    temp.setBlock(pos);
                    wall.addChild(temp);
                } else if (type == GameElement.BlockType.BOX) {
                    Box temp = new Box();
                    temp.setMap(mapPlay);
                    temp.setBlock(pos);
                    box.addChild(temp);
                }
            }
        }

        Human player = new Human();
        player.name = "player";
        player.defaultBlock.set(1, 1);
        player.setMap(mapPlay);
        actor.addChild(player);

        Human playeralt = new Human();
        playeralt.name = "playeralt";
        playeralt.defaultBlock.set(13, 13);
        playeralt.setMap(mapPlay);
        playeralt.leftKey = Input.Keys.LEFT;
        playeralt.rightKey = Input.Keys.RIGHT;
        playeralt.upKey = Input.Keys.UP;
        playeralt.downKey = Input.Keys.DOWN;
        playeralt.bombKey = Input.Keys.ENTER;
        actor.addChild(playeralt);

        TagManager tagManager = new TagManager();
        tagManager.position.set(20, 200);
        root.addChild(tagManager);
        tagManager.connectPlayer(player);
        tagManager.connectPlayer(playeralt);

    }

}

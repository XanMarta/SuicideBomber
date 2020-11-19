package com.suicidebomber.scene;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.suicidebomber.element.*;
import com.suicidebomber.engine.*;
import com.suicidebomber.structure.GameElement;
import com.suicidebomber.structure.Scene;


public class PlayGround extends Scene {

    public PlayerManager playerManager;
    public Canvas2D bomb;
    public Canvas2D fire;
    public Canvas2D box;
    public Canvas2D wall;
    public Canvas2D item;

    public void create() {
        super.create();

        TileMap mapPlay = new TileMap();
        mapPlay.blockSize.set(GameElement.blockSize);
        mapPlay.name = "mapplay";
        mapPlay.generateMap(GameElement.mapLoader.loadMap("SANDSTORM"));
        mapPlay.position.set(GameElement.mapPosition);
        root.addChild(mapPlay);

        bomb = new Canvas2D();
        bomb.name = "bomb";
        mapPlay.addChild(bomb);

        fire = new Canvas2D();
        fire.name = "fire";
        mapPlay.addChild(fire);

        box = new Canvas2D();
        box.name = "box";
        mapPlay.addChild(box);

        wall = new Canvas2D();
        wall.name = "wall";
        mapPlay.addChild(wall);

        item = new Canvas2D();
        item.name = "item";
        mapPlay.addChild(item);

        playerManager = new PlayerManager();
        playerManager.name = "playerManager";
        mapPlay.addChild(playerManager);

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
        player.playerName = "Don";
        playerManager.addChild(player);
        playerManager.addPlayer(player);

        Human playeralt = new Human();
        playeralt.name = "playeralt";
        playeralt.defaultBlock.set(13, 13);
        playeralt.setMap(mapPlay);
        playeralt.leftKey = Input.Keys.LEFT;
        playeralt.rightKey = Input.Keys.RIGHT;
        playeralt.upKey = Input.Keys.UP;
        playeralt.downKey = Input.Keys.DOWN;
        playeralt.bombKey = Input.Keys.ENTER;
        playeralt.playerName = "Bailey";
        playerManager.addChild(playeralt);
        playerManager.addPlayer(playeralt);

        WalkingBot walkingBot = new WalkingBot();
        walkingBot.name = "walkingbot";
        walkingBot.defaultBlock.set(13, 1);
        walkingBot.setMap(mapPlay);
        walkingBot.playerName = "BOT";
        playerManager.addChild(walkingBot);
        playerManager.addPlayer(walkingBot);

        TagManager tagManager = new TagManager();
        tagManager.position.set(20, 200);
        root.addChild(tagManager);
        tagManager.connectPlayer(player);
        tagManager.connectPlayer(playeralt);
        tagManager.connectPlayer(walkingBot);
    }

}

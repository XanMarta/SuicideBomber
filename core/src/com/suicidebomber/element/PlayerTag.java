package com.suicidebomber.element;

import com.suicidebomber.engine.Canvas2D;
import com.suicidebomber.engine.Label;
import com.suicidebomber.engine.Node;
import com.suicidebomber.engine.Sprite;
import com.suicidebomber.structure.GameElement;


public class PlayerTag extends Canvas2D {

    private Canvas2D bombs;
    private Canvas2D hearts;
    private Canvas2D powers;
    private Canvas2D speeds;
    private Label playerName;

    public boolean hasPlayer = false;

    public void create() {
        super.create();
        Sprite mainImage = new Sprite();
        mainImage.image = "PLAYER_TAG";
        addChild(mainImage);
        bombs = new Canvas2D();
        hearts = new Canvas2D();
        powers = new Canvas2D();
        speeds = new Canvas2D();
        addChild(bombs);
        addChild(hearts);
        addChild(powers);
        addChild(speeds);
        int range = 180 / GameElement.max_element;
        for (int i = 0; i < GameElement.max_element; i++) {
            Sprite bomb = new Sprite();
            bomb.image = "ICON_BOMB";
            bomb.position.set(110 + i * range, 115);
            bombs.addChild(bomb);
            Sprite heart = new Sprite();
            heart.image = "ICON_HEART";
            heart.position.set(110 + i * range, 80);
            hearts.addChild(heart);
            Sprite power = new Sprite();
            power.image = "ICON_POWER";
            power.position.set(110 + i * range, 45);
            powers.addChild(power);
            Sprite speed = new Sprite();
            speed.image = "ICON_SPEED";
            speed.position.set(110 + i * range, 10);
            speeds.addChild(speed);
        }
        playerName = new Label();
        playerName.position.set(10, 30);
        addChild(playerName);
        update(null);
    }

    public void update(Player player) {
        if (player == null) {
            for (Node node : bombs.getChildren()) {
                ((Sprite) node).visible = false;
            }
            for (Node node : hearts.getChildren()) {
                ((Sprite) node).visible = false;
            }
            for (Node node : powers.getChildren()) {
                ((Sprite) node).visible = false;
            }
            for (Node node : speeds.getChildren()) {
                ((Sprite) node).visible = false;
            }
        } else {
            for (int i = 0; i < GameElement.max_element; i++) {
                ((Sprite) bombs.getChild(i)).visible = (i < player.bomb);
                ((Sprite) hearts.getChild(i)).visible = (i < player.heart);
                ((Sprite) powers.getChild(i)).visible = (i < player.power);
                ((Sprite) speeds.getChild(i)).visible = (i < player.speed);
            }
            playerName.text = player.playerName;
        }
    }
}

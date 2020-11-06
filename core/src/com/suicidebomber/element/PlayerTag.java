package com.suicidebomber.element;

import com.suicidebomber.engine.Node;
import com.suicidebomber.engine.Node2D;
import com.suicidebomber.engine.Sprite;
import com.suicidebomber.game.GameElement;


public class PlayerTag extends Node2D {

    public Node2D bombs;
    public Node2D hearts;
    public Node2D powers;
    public Node2D speeds;

    public void create() {
        super.create();
        Sprite mainImage = new Sprite();
        mainImage.image = "PLAYER_TAG";
        addChild(mainImage);
        bombs = new Node2D();
        hearts = new Node2D();
        powers = new Node2D();
        speeds = new Node2D();
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
    }

    public void update(Player player) {
        if (player == null) {
            for (Node node : bombs.getChildren()) {
                ((Sprite) node).showing = false;
            }
            for (Node node : hearts.getChildren()) {
                ((Sprite) node).showing = false;
            }
            for (Node node : powers.getChildren()) {
                ((Sprite) node).showing = false;
            }
            for (Node node : speeds.getChildren()) {
                ((Sprite) node).showing = false;
            }
        } else {
            for (int i = 0; i < GameElement.max_element; i++) {
                ((Sprite) bombs.getChild(i)).showing = (i < player.bomb);
                ((Sprite) hearts.getChild(i)).showing = (i < player.heart);
                ((Sprite) powers.getChild(i)).showing = (i < player.power);
                ((Sprite) speeds.getChild(i)).showing = (i < player.speed);
            }
        }
    }
}

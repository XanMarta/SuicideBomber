package com.suicidebomber.element;

import com.suicidebomber.engine.Canvas2D;
import com.suicidebomber.scene.Lobby;
import com.suicidebomber.structure.GameElement;

import java.util.ArrayList;


public class PlayerManager extends Canvas2D {

    public ArrayList<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
        player.connect_signal("player_die", this, "player_die");
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("player_die")) {
            player_die();
        }
    }

    public void player_die() {
        int remainPlayer = 0;
        for (Player player : players) {
            if (player.isLiving) {
                remainPlayer += 1;
            }
        }
        if (remainPlayer <= 1) {
            GameElement.sceneManager.changeSceneTo(new Lobby());
        }
    }

    public void dispose() {
        super.dispose();
        players.clear();
    }

}

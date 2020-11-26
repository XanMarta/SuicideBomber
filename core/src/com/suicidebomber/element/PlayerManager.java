package com.suicidebomber.element;

import com.suicidebomber.engine.Canvas2D;
import java.util.ArrayList;

// Signal: end_game

public class PlayerManager extends Canvas2D {

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> bots = new ArrayList<>();
    private int remainPlayer = 0;
    private int remainBot = 0;

    public void addPlayer(Player player) {
        if (player instanceof Bot) {
            bots.add(player);
            remainBot += 1;
        } else {
            players.add(player);
            remainPlayer += 1;
        }
        player.connect_signal("player_die", this, "player_die");
    }

    public void execute_signal(String signal) {
        super.execute_signal(signal);
        if (signal.equals("player_die")) {
            player_die();
        }
    }

    public void player_die() {
        remainPlayer = 0;
        remainBot = 0;
        for (Player player : players) {
            if (player.isLiving) {
                remainPlayer += 1;
            }
        }
        for (Player bot : bots) {
            if (bot.isLiving) {
                remainBot += 1;
            }
        }
        if (remainPlayer < 1) {
            emit_signal("end_game");
        } else if (remainPlayer == 1 && remainBot < 1) {
            emit_signal("end_game");
        }
    }

    public String getWinner() {
        if (remainPlayer < 1) {
            return "LOSE";
        } else if (remainPlayer == 1 && remainBot < 1) {
            for (Player player : players) {
                if (player.isLiving) {
                    return player.playerName;
                }
            }
        } else {
            return "DRAW";
        }
        return "";
    }

    public void dispose() {
        super.dispose();
        players.clear();
    }

}

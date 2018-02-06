package com.example.bianca.snookerscoretracker;

import java.io.Serializable;

/**
 * Created by bianca on 05/02/2018.
 */

public class Team implements Serializable{

    public String name;
    public Player player1;
    public Player player2;
    public int score;

    public Team(String teamName, String playerName1, String playerName2){
        this.name = teamName;
        this.player1 = new Player(playerName1);
        this.player2 = new Player(playerName2);
        this.score = 0;
    }

    public Team(Player p1, Player p2){
        this.player1 = p1;
        this.player2 = p2;
        this.score = 0;
    }

    public void increaseScore(int value){
        if(value > 0)
            this.score += value;
    }
}

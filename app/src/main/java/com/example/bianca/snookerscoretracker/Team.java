package com.example.bianca.snookerscoretracker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bianca on 05/02/2018.
 */

public class Team implements Serializable{

    public String name;
    public int score;
    public List<Player> players;
    public int id;

    public Team(String teamName){
        this.name = teamName;
        this.score = 0;
        this.players = new ArrayList<>();
        this.id = 0;
    }

    public void addPlayer(Player player){
        this.players.add(player);
    }

    public void increaseScore(int value){
        if(value > 0)
            this.score += value;
    }
}

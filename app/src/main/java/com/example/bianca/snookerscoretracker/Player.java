package com.example.bianca.snookerscoretracker;

import java.io.Serializable;

/**
 * Created by bianca on 05/02/2018.
 */

public class Player implements Serializable{

    public String name;
    public int score;
    public int id;

    public Player(String name){
        this.name = name;
        this.score = 0;
        this.id = 0;
    }

    public void increaseScore(int value){
        if(value >= 0)
            this.score += value;
    }
}

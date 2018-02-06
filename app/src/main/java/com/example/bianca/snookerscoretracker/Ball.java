package com.example.bianca.snookerscoretracker;

/**
 * Created by bianca on 06/02/2018.
 */

public class Ball {

    public int viewID;
    public int value;
    public int quantity;
    public boolean coloured;
    public int colourID;

    public Ball(int id, int value, int quantity, boolean coloured, int colourID){
        this.viewID = id;
        this.value = value;
        this.quantity = quantity;
        this.coloured = coloured;
        this.colourID = colourID;
    }
}

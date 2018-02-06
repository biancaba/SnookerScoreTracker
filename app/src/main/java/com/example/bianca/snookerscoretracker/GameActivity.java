package com.example.bianca.snookerscoretracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    private static final int RED_BALL_VALUE = 1;
    private static final int YELLOW_BALL_VALUE = 2;
    private static final int GREEN_BALL_VALUE = 3;
    private static final int BROWN_BALL_VALUE = 4;
    private static final int BLUE_BALL_VALUE = 5;
    private static final int PINK_BALL_VALUE = 6;
    private static final int BLACK_BALL_VALUE = 7;

    public List<Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        teams = (List<Team>) intent.getSerializableExtra("game_information");

        putNames();
        putScores();
    }

    private void putNames(){
        TextView t_one_view = findViewById(R.id.team_one);
        t_one_view.setText(teams.get(0).name);

        TextView p_one_view = findViewById(R.id.player_one_name);
        p_one_view.setText(teams.get(0).player1.name);

        TextView p_two_view = findViewById(R.id.player_two_name);
        p_two_view.setText(teams.get(0).player2.name);

        TextView t_two_view = findViewById(R.id.team_two);
        t_two_view.setText(teams.get(1).name);

        TextView p_three_view = findViewById(R.id.player_three_name);
        p_three_view.setText(teams.get(1).player1.name);

        TextView p_four_view = findViewById(R.id.player_four_name);
        p_four_view.setText(teams.get(1).player2.name);
    }

    private void putScores(){
        TextView t_one_view = findViewById(R.id.team_one_score);
        t_one_view.setText(String.valueOf(teams.get(0).score));

        TextView p_one_view = findViewById(R.id.player_one_score);
        p_one_view.setText(String.valueOf(teams.get(0).player1.score));

        TextView p_two_view = findViewById(R.id.player_two_score);
        p_two_view.setText(String.valueOf(teams.get(0).player2.score));

        TextView t_two_view = findViewById(R.id.team_two_score);
        t_two_view.setText(String.valueOf(teams.get(1).score));

        TextView p_three_view = findViewById(R.id.player_three_score);
        p_three_view.setText(String.valueOf(teams.get(1).player1.score));

        TextView p_four_view = findViewById(R.id.player_four_score);
        p_four_view.setText(String.valueOf(teams.get(1).player2.score));
    }

    public void onBallClick(View view){

        int value = 0;

        switch (view.getId()){
            case R.id.red_ball:
                value = RED_BALL_VALUE;break;
            case R.id.yellow_ball:
                value = YELLOW_BALL_VALUE;break;
            case R.id.green_ball:
                value = GREEN_BALL_VALUE;break;
            case R.id.brown_ball:
                value = BROWN_BALL_VALUE;break;
            case R.id.blue_ball:
                value = BLUE_BALL_VALUE;break;
            case R.id.pink_ball:
                value = PINK_BALL_VALUE;break;
            case R.id.black_ball:
                value = BLACK_BALL_VALUE;break;
        }

        teams.get(0).player1.increaseScore(value);
        TextView player = findViewById(R.id.player_one_score);
        player.setText(String.valueOf(teams.get(0).player1.score));
    }
}

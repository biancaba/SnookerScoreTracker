package com.example.bianca.snookerscoretracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    public List<Team> teams;

    Team bestTeam;
    Player player1;
    Player player2;
    Player player3;
    Player player4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Intent intent = getIntent();
        teams = (List<Team>) intent.getSerializableExtra("frame_results");

        bestTeam = teams.get(0);
        player1 = new Player("", -1);
        player2 = new Player("", -1);
        player3 = new Player("", -1);
        player4 = new Player("", -1);

        for(int i = 0; i < teams.size(); i++){
            Team currentTeam = teams.get(i);

            if(currentTeam.score > bestTeam.score)
                bestTeam = currentTeam;

            for(int j = 0; j < currentTeam.players.size(); j++){
                Player currentPlayer = currentTeam.players.get(j);

                if(currentPlayer.score > player1.score) {
                    player4 = player3;
                    player3 = player2;
                    player2 = player1;
                    player1 = currentPlayer;
                }
                else if(currentPlayer.score > player2.score){
                    player4 = player3;
                    player3 = player2;
                    player2 = currentPlayer;
                }
                else if(currentPlayer.score > player3.score){
                    player4 = player3;
                    player3 = currentPlayer;
                }
                else if(currentPlayer.score > player4.score){
                    player4 = currentPlayer;
                }
            }
        }
        putWinnerNames();
        putWinnerScores();
    }

    private void putWinnerNames(){
        TextView team = findViewById(R.id.win_team);
        team.setText(bestTeam.name);

        TextView player = findViewById(R.id.win_player_one);
        player.setText(player1.name);
        player = findViewById(R.id.win_player_two);
        player.setText(player2.name);
        player = findViewById(R.id.win_player_three);
        player.setText(player3.name);
        player = findViewById(R.id.win_player_four);
        player.setText(player4.name);
    }

    private void putWinnerScores(){
        TextView team = findViewById(R.id.score_team);
        team.setText(String.valueOf(bestTeam.score));

        TextView player = findViewById(R.id.score_player_one);
        player.setText(String.valueOf(player1.score));
        player = findViewById(R.id.score_player_two);
        player.setText(String.valueOf(player2.score));
        player = findViewById(R.id.score_player_three);
        player.setText(String.valueOf(player3.score));
        player = findViewById(R.id.score_player_four);
        player.setText(String.valueOf(player4.score));
    }

    public void onWinExitGameClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onNewFrameClick(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("game_information", (Serializable) teams);
        startActivity(intent);
    }
}

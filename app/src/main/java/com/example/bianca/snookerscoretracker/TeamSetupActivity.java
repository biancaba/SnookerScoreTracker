package com.example.bianca.snookerscoretracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeamSetupActivity extends AppCompatActivity {

    public Team teamOne;
    public Team teamTwo;

    public List<Team> teams = new ArrayList<Team>(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_setup);
    }

    public void onNextTeamClick(View view) {
        Button nextTeam = findViewById(R.id.next_team);
        nextTeam.setVisibility(View.INVISIBLE);
        Button finishSetup = findViewById(R.id.finish_setup);
        finishSetup.setVisibility(View.VISIBLE);

        EditText t1 = findViewById(R.id.win_team);
        EditText p1 = findViewById(R.id.player_one);
        EditText p2 = findViewById(R.id.player_two);

        teamOne = new Team(t1.getText().toString());
        teamOne.addPlayer(new Player(p1.getText().toString()));
        teamOne.addPlayer(new Player(p2.getText().toString()));

        t1.setText(R.string.team_2);
        p1.setText(R.string.player_1);
        p2.setText(R.string.player_2);
    }

    public void onFinishSetupClick(View view) {
        EditText t1 = findViewById(R.id.win_team);
        EditText p1 = findViewById(R.id.player_one);
        EditText p2 = findViewById(R.id.player_two);

        teamTwo = new Team(t1.getText().toString());
        teamTwo.addPlayer(new Player(p1.getText().toString()));
        teamTwo.addPlayer(new Player(p2.getText().toString()));

        teams.add(teamOne);
        teams.add(teamTwo);

        Intent intent = new Intent(this, TeamViewActivity.class);
        intent.putExtra("setup_information", (Serializable) teams);
        startActivity(intent);
    }
}

package com.example.bianca.snookerscoretracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private boolean emptyTextViews(){
        EditText team = findViewById(R.id.first_team);
        EditText player1 = findViewById(R.id.player_one);
        EditText player2 = findViewById(R.id.player_two);

        return isEmpty(team) || isEmpty(player1) || isEmpty(player2);
    }

    public void onNextTeamClick(View view) {
        if(!emptyTextViews()) {
            Button nextTeam = findViewById(R.id.next_team);
            nextTeam.setVisibility(View.INVISIBLE);
            Button finishSetup = findViewById(R.id.finish_setup);
            finishSetup.setVisibility(View.VISIBLE);

            EditText t1 = findViewById(R.id.first_team);
            EditText p1 = findViewById(R.id.player_one);
            EditText p2 = findViewById(R.id.player_two);

            teamOne = new Team(t1.getText().toString());
            teamOne.addPlayer(new Player(p1.getText().toString()));
            teamOne.addPlayer(new Player(p2.getText().toString()));

            t1.setText("");
            p1.setText("");
            p2.setText("");
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "Please enter names";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void onFinishSetupClick(View view) {
        if(!emptyTextViews()) {
            EditText t1 = findViewById(R.id.first_team);
            EditText p1 = findViewById(R.id.player_one);
            EditText p2 = findViewById(R.id.player_two);

            teamTwo = new Team(t1.getText().toString());
            teamTwo.addPlayer(new Player(p1.getText().toString()));
            teamTwo.addPlayer(new Player(p2.getText().toString()));

            teams.add(teamOne);
            teams.add(teamTwo);

            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("game_information", (Serializable) teams);
            startActivity(intent);
        }
        else{
            Context context = getApplicationContext();
            CharSequence text = "Please enter names";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}

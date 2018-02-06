package com.example.bianca.snookerscoretracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeamViewActivity extends AppCompatActivity {

    public List<Team> teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_view);

        Intent intent = getIntent();
        teams = (List<Team>) intent.getSerializableExtra("setup_information");

        TextView t_one_view = findViewById(R.id.team_one_name);
        t_one_view.setText(teams.get(0).name);

        TextView p_one_view = findViewById(R.id.p_one_name);
        p_one_view.setText(teams.get(0).player1.name);

        TextView p_two_view = findViewById(R.id.p_two_name);
        p_two_view.setText(teams.get(0).player2.name);

        TextView t_two_view = findViewById(R.id.team_two_name);
        t_two_view.setText(teams.get(1).name);

        TextView p_three_view = findViewById(R.id.p_three_name);
        p_three_view.setText(teams.get(1).player1.name);

        TextView p_four_name = findViewById(R.id.p_four_name);
        p_four_name.setText(teams.get(1).player2.name);
    }

    public void onStartGameClick(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("game_information", (Serializable) teams);
        startActivity(intent);
    }
}

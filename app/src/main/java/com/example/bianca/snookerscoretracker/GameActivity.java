package com.example.bianca.snookerscoretracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameActivity extends AppCompatActivity {

    private static final int RED_BALL_VALUE = 1;
    private static final int YELLOW_BALL_VALUE = 2;
    private static final int GREEN_BALL_VALUE = 3;
    private static final int BROWN_BALL_VALUE = 4;
    private static final int BLUE_BALL_VALUE = 5;
    private static final int PINK_BALL_VALUE = 6;
    private static final int BLACK_BALL_VALUE = 7;

    private static final int FOUL = 4;

    public List<Team> teams;
    public List<Ball> balls = new ArrayList<>(7);

    public Queue<Pair<Integer, Integer>> activeTurns = new LinkedList<>();
    public int activeBall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        teams = (List<Team>) intent.getSerializableExtra("game_information");

        teams.get(0).id = R.id.team_one_score;
        teams.get(0).players.get(0).id = R.id.player_one_score;
        teams.get(0).players.get(1).id = R.id.player_two_score;

        teams.get(1).id = R.id.team_two_score;
        teams.get(1).players.get(0).id = R.id.player_three_score;
        teams.get(1).players.get(1).id = R.id.player_four_score;

        putNames();
        putScores();
        putStreaks();

        for(int i=0; i<teams.size(); i++){
            activeTurns.add(new Pair<>(i, 0));
        }

        balls.add(new Ball(R.id.red_ball, 1, 15, false, R.color.red));
        balls.add(new Ball(R.id.yellow_ball,2, 1, true, R.color.yellow));
        balls.add(new Ball(R.id.green_ball, 3,1, true, R.color.green));
        balls.add(new Ball(R.id.brown_ball, 4,1, true, R.color.brown));
        balls.add(new Ball(R.id.blue_ball, 5,1, true, R.color.blue));
        balls.add(new Ball(R.id.pink_ball, 6,1, true, R.color.pink));
        balls.add(new Ball(R.id.black_ball, 7,1, true, R.color.black));

        for(int i = 0; i < balls.size(); i++){
            Button ballBtn = findViewById(balls.get(i).viewID);
            ballBtn.setText(String.valueOf(balls.get(i).quantity));
        }
        activeBall = 0;
    }

    private void putNames(){
        TextView t_one_view = findViewById(R.id.team_one);
        t_one_view.setText(teams.get(0).name);

        TextView p_one_view = findViewById(R.id.player_one_name);
        p_one_view.setText(teams.get(0).players.get(0).name);

        TextView p_two_view = findViewById(R.id.player_two_name);
        p_two_view.setText(teams.get(0).players.get(1).name);

        TextView t_two_view = findViewById(R.id.team_two);
        t_two_view.setText(teams.get(1).name);

        TextView p_three_view = findViewById(R.id.player_three_name);
        p_three_view.setText(teams.get(1).players.get(0).name);

        TextView p_four_view = findViewById(R.id.player_four_name);
        p_four_view.setText(teams.get(1).players.get(1).name);
    }

    private void putScores(){
        TextView t_one_view = findViewById(R.id.team_one_score);
        t_one_view.setText(String.valueOf(teams.get(0).score));

        TextView p_one_view = findViewById(R.id.player_one_score);
        p_one_view.setText(String.valueOf(teams.get(0).players.get(0).score));

        TextView p_two_view = findViewById(R.id.player_two_score);
        p_two_view.setText(String.valueOf(teams.get(0).players.get(1).score));

        TextView t_two_view = findViewById(R.id.team_two_score);
        t_two_view.setText(String.valueOf(teams.get(1).score));

        TextView p_three_view = findViewById(R.id.player_three_score);
        p_three_view.setText(String.valueOf(teams.get(1).players.get(0).score));

        TextView p_four_view = findViewById(R.id.player_four_score);
        p_four_view.setText(String.valueOf(teams.get(1).players.get(1).score));
    }

    private void putStreaks(){
        TextView streakView = findViewById(R.id.streak_team_one);
        streakView.setText(String.valueOf(teams.get(0).streak));
        streakView = findViewById(R.id.streak_team_two);
        streakView.setText(String.valueOf(teams.get(1).streak));
    }

    public void onRedBallClick(View view){
        int value = RED_BALL_VALUE;
        changeScore(value);

        balls.get(0).quantity--;
        int redQuantity = balls.get(0).quantity;

        if(redQuantity == 0){
            activeBall++;
            disableOneBall(0);
            enableOneBall(activeBall);
        }
        else
            enableBallButtons(false, true);
    }

    public void onColouredBallClick(View view){

        int value = 0;
        switch (activeBall){
            case 0:
                switch (view.getId()) {
                    case R.id.yellow_ball:
                        value = YELLOW_BALL_VALUE;
                        break;
                    case R.id.green_ball:
                        value = GREEN_BALL_VALUE;
                        break;
                    case R.id.brown_ball:
                        value = BROWN_BALL_VALUE;
                        break;
                    case R.id.blue_ball:
                        value = BLUE_BALL_VALUE;
                        break;
                    case R.id.pink_ball:
                        value = PINK_BALL_VALUE;
                        break;
                    case R.id.black_ball:
                        value = BLACK_BALL_VALUE;
                        break;
                }
                changeScore(value);
                enableBallButtons(true, false);
                break;
            default:
                changeScore(activeBall+1);
                balls.get(activeBall).quantity--;

                for(int i = 0; i < balls.size(); i++)
                    disableOneBall(i);

                activeBall++;
                if(activeBall < 7)
                    enableOneBall(activeBall);
                else{
                    Button btn = findViewById(R.id.end_frame);
                    btn.setEnabled(true);
                    btn = findViewById(R.id.foul);
                    btn.setEnabled(false);
                    btn = findViewById(R.id.next_player);
                    btn.setEnabled(false);
                }
        }
    }

    private void disableOneBall(int index){
        Button btn;
        btn = findViewById(balls.get(index).viewID);
        btn.setEnabled(false);
        btn.setText(String.valueOf(balls.get(index).quantity));
        btn.setAlpha(0.5f);
    }

    private void enableOneBall(int index){
        Button btn;
        btn = findViewById(balls.get(index).viewID);
        btn.setEnabled(true);
        btn.setText(String.valueOf(balls.get(index).quantity));
        btn.setAlpha(1f);
    }

    private void enableBallButtons(boolean redBallValue, boolean colouredBallValue){
        Button btn;
        btn = findViewById(balls.get(0).viewID);
        btn.setEnabled(redBallValue);
        btn.setText(String.valueOf(balls.get(0).quantity));
        if(redBallValue)
            btn.setAlpha(1f);
        else
            btn.setAlpha(0.5f);

        for(int i = 1; i < balls.size(); i++){
            btn = findViewById(balls.get(i).viewID);
            btn.setEnabled(!redBallValue);
            btn.setText(String.valueOf(balls.get(i).quantity));
            if(colouredBallValue)
                btn.setAlpha(1f);
            else
                btn.setAlpha(0.5f);
        }
    }

    private void changeScore(int value){
        Team activeTeam = teams.get(activeTurns.peek().first);
        Player activePlayer = activeTeam.players.get(activeTurns.peek().second);
        activeTeam.increaseScore(value);
        activePlayer.increaseScore(value);

        TextView activeTeamView = findViewById(activeTeam.id);
        activeTeamView.setText(String.valueOf(activeTeam.score));
        TextView activePlayerView = findViewById(activePlayer.id);
        activePlayerView.setText(String.valueOf(activePlayer.score));
    }

    public void onNextPlayerClick(View view){
        Pair<Integer, Integer> activePair = activeTurns.remove();
        int team = activePair.first;
        int player = activePair.second;

        ImageView activePlayerIconView = findViewById(findImageView(team, player));
        activePlayerIconView.setVisibility(View.INVISIBLE);

        player++;
        if (player == teams.get(team).players.size())
            player = 0;
        activeTurns.add(new Pair<>(team, player));

        activePair = activeTurns.peek();
        activePlayerIconView = findViewById(findImageView(activePair.first, activePair.second));
        activePlayerIconView.setVisibility(View.VISIBLE);

        if(activeBall == 0){
            for (int i = 0; i < balls.size(); i++) {
                enableOneBall(i);
            }
        }
        else {
            enableOneBall(activeBall);
        }
    }

    private int findImageView(int team, int player){
        if(team == 0 && player == 0)
            return R.id.player_one_icon;
        else if(team == 0 && player == 1)
            return R.id.player_two_icon;
        else if(team == 1 && player == 0)
            return R.id.player_three_icon;
        else
            return R.id.player_four_icon;
    }

    public void onFoulClick(View view){
        Team activeTeam = teams.get(activeTurns.peek().first);

        for(int i = 0; i < teams.size(); i++){
            Team t = teams.get(i);
            if(t.id != activeTeam.id){
                t.increaseScore(FOUL);
                TextView text = findViewById(t.id);
                text.setText(String.valueOf(t.score));
            }
        }
        onNextPlayerClick(null);
    }

    public void onExitGameClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onEndFrameClick(View view){
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra("frame_results", (Serializable) teams);
        startActivity(intent);
    }
}

package com.example.android.rugbyscorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int scoreTeamA = 0;
    int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * Team A scores a Try. https://en.wikipedia.org/wiki/Rugby_union#Scoring
     * Increase the score for Team A by 5 points.
     */
    public void tryForTeamA(View v) {
        scoreTeamA = scoreTeamA + 5;
        displayScoreForTeamA(scoreTeamA);
    }
    /**
     * Team A scores a Conversion Kick. https://en.wikipedia.org/wiki/Rugby_union#Scoring
     * Increase the score for Team A by 2 points.
     */
    public void conversionKickForTeamA(View v) {
        scoreTeamA = scoreTeamA + 2;
        displayScoreForTeamA(scoreTeamA);
    }
    /**
     * Team A scores a Penalty Kick.  https://en.wikipedia.org/wiki/Rugby_union#Scoring
     * Increase the score for Team A by 3 points.
     */
    public void penaltyKickForTeamA(View v) {
        scoreTeamA = scoreTeamA + 3;
        displayScoreForTeamA(scoreTeamA);
    }
    /**
     * Displays the given score for Team A.
     */
    public void displayScoreForTeamA(int score) {
        TextView scoreView = findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }
    /**
     * Team B scores a Try. https://en.wikipedia.org/wiki/Rugby_union#Scoring
     * Increase the score for Team B by 5 points.
     */
    public void tryForTeamB(View v) {
        scoreTeamB = scoreTeamB + 5;
        displayScoreForTeamB(scoreTeamB);
    }
    /**
     * Team B scores a Conversion Kick. https://en.wikipedia.org/wiki/Rugby_union#Scoring
     * Increase the score for Team B by 2 points.
     */
    public void conversionKickForTeamB(View v) {
        scoreTeamB = scoreTeamB + 2;
        displayScoreForTeamB(scoreTeamB);
    }
    /**
     *
     * Team B scores a Penalty Kick.  https://en.wikipedia.org/wiki/Rugby_union#Scoring
     * Increase the score for Team B by 3 points.
     */
    public void penaltyKickForTeamB(View v) {
        scoreTeamB = scoreTeamB + 3;
        displayScoreForTeamB(scoreTeamB);
    }
    /**
     * Displays the given score for Team A.
     */
    public void displayScoreForTeamB(int score) {
        TextView scoreView = findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     *
     * Resets the score textViews and sets the variables to 0
     */
    public void resetScores(View v){
        TextView scoreView1 = findViewById(R.id.team_a_score);
        TextView scoreView2 = findViewById(R.id.team_b_score);
        scoreView1.setText(String.valueOf(0));
        scoreView2.setText(String.valueOf(0));
        scoreTeamA = 0;
        scoreTeamB = 0;
    }
}

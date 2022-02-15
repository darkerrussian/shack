package com.example.whackamole;

import static com.example.whackamole.MainActivity.DATA_NAME;

import static com.example.whackamole.MainActivity.INT_RECORD;
import static com.example.whackamole.MainActivity.INT_SCORE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameOver extends AppCompatActivity {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    int recordScore;
    TextView scoreText, recordText;
    int currentScore;
    Button playButton, menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        scoreText = findViewById(R.id.textScoreFinish);
        recordText = findViewById(R.id.textRecordFinish);
        playButton = findViewById(R.id.play_again);
        menuButton = findViewById(R.id.menubtn);

        //GET RECORD AND SCORE
        prefs = this.getSharedPreferences(DATA_NAME, MODE_PRIVATE);

        //Maybe try catch
        recordScore = prefs.getInt(INT_RECORD, 0);
        recordText.setText("Record: " + recordScore);

        currentScore = prefs.getInt(INT_SCORE, 0);
        scoreText.setText("Score: "+currentScore);


        //LISTENERS

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = prefs.edit();
                editor.putInt(INT_RECORD, recordScore);
                editor.putInt(INT_SCORE, currentScore);
                editor.commit();
                Intent intent = new Intent(GameOver.this, MainActivity.class);
                startActivity(intent);
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = prefs.edit();
                editor.putInt(INT_RECORD, recordScore);
                editor.putInt(INT_SCORE, currentScore);
                editor.commit();
                Intent intent = new Intent(GameOver.this, MainMenu.class);
                startActivity(intent);
            }
        });







    }
}
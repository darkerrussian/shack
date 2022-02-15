package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageButton btn1, btn2, btn3, btn4, btn5, btn6;

    TextView textScore, textTimer;

    CountDownTimer timer;
    CountDownTimer timer1;
    int previous_id;
    int currentScore;
    int recordScore;
    int tapInt;
    public static final String DATA_NAME = "RecordData";
    public static final String INT_SCORE = "int_score";
    public static final String INT_RECORD = "int_record";
    public static final String enemyId = "enemy";
    public static final String friendlyId = "friend";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = this.getSharedPreferences(DATA_NAME, MODE_PRIVATE);

        //Maybe try catch
            recordScore = prefs.getInt(INT_RECORD, 0);


        currentScore = 0;

        textScore = findViewById(R.id.textScore);
        textTimer = findViewById(R.id.textTimer);

        btn1 =findViewById(R.id.btn1);
        btn2 =findViewById(R.id.btn2);
        btn3 =findViewById(R.id.btn3);
        btn4 =findViewById(R.id.btn4);
        btn5 =findViewById(R.id.btn5);
        btn6 =findViewById(R.id.btn6);

        //Objects as ImageButtons
        ArrayList<ImageButton> list = new ArrayList<>();
        list.add(btn1);
        list.add(btn2);
        list.add(btn3);
        list.add(btn4);
        list.add(btn5);
        list.add(btn6);





        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                textTimer.setText("Timer: " + millisUntilFinished/1000);

                Random rand = new Random();


                int i = rand.nextInt(6);
                tapInt = 0;







                ImageButton enemy = list.get(i);

                enemy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Check for only on tap on mole
                        if(tapInt == 0) {
                            if (enemy.getTag() == enemyId) {
                                currentScore++;
                                textScore.setText("Score: " + currentScore);
                                tapInt++;
                            }
                        }
                    }
                });

                    enemy.setImageResource(R.drawable.img);
                    enemy.setTag(enemyId);


                timer1 = new CountDownTimer(500, 500) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }

                    @Override
                    public void onFinish() {
                        if(enemy.getTag() == enemyId) {
                            enemy.setImageResource(R.drawable.pit);
                            enemy.setTag(friendlyId);
                        }

                    }
                };
                timer1.start();
            }

            @Override
            public void onFinish() {

                if(recordScore==0 || recordScore< currentScore)
                    recordScore = currentScore;

                editor = prefs.edit();
                editor.putInt(INT_RECORD, recordScore);
                editor.putInt(INT_SCORE, currentScore);
                editor.commit();
                //textTimer.setText("int work " + recordScore);
                Intent intent = new Intent(MainActivity.this, GameOver.class);
                startActivity(intent);

            }
        };
        timer.start();





    }


   }
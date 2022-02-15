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

public class MainMenu extends AppCompatActivity {

    TextView textRecord;
    Button playBtn;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    int record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        textRecord = findViewById(R.id.textRecordMain);
        playBtn = findViewById(R.id.playButton);

        prefs = this.getSharedPreferences(DATA_NAME, MODE_PRIVATE);


        record = prefs.getInt(INT_RECORD, 0);
        textRecord.setText("Record: " + record);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor = prefs.edit();
                editor.putInt(INT_RECORD, record);

                editor.commit();
                Intent intent = new Intent(MainMenu.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}
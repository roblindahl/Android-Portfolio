package com.example.android.dinosaurquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Set;

public class DisplayAnswersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_answers);

        //Get the Intent that opened the activity and Set the string passed
        Intent intent = getIntent();
        String answers = intent.getStringExtra(MainActivity.ANSWER_MESSAGE);

        //Get the TextView by ID and Set the answer variable as the text
        TextView textView = findViewById(R.id.answers);
        textView.setText(answers);
    }
}

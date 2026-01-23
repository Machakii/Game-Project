package com.example.numera;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class GameActivity extends AppCompatActivity {


    private Integer firstNumber = 0, secondNumber = 0;

    private String diff = "", question = "";
    private Integer score = 0;
    private int delay = 1000;
    int progress;
    private TextView txtQuestion, txtScore, txtDiff = null;
    private Button btnOne, btnTwo, btnThree, btnFour = null;
    private ProgressBar progBar = null;
    private final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtScore = findViewById(R.id.score);
        txtDiff = findViewById(R.id.diff);
        txtQuestion = findViewById(R.id.question);

        btnOne = findViewById(R.id.btn_one);
        btnTwo = findViewById(R.id.btn_two);
        btnThree = findViewById(R.id.btn_three);
        btnFour = findViewById(R.id.btn_four);

        progBar = findViewById(R.id.progressBar);
        progBar.setProgress(0);


    }





}
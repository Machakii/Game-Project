package com.example.numera;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameOverActivity extends AppCompatActivity {

    Button btnPlayAgain, btnHome;
    TextView score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_game_over);

        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnHome = findViewById(R.id.btnHome);
        score = findViewById(R.id.score);

        Intent intent = getIntent();
        String scored = intent.getStringExtra("score");

        score.setText(scored);

        btnPlayAgain.setOnClickListener(view -> {
            startActivity(new Intent(GameOverActivity.this, GameActivity.class));
            finish();
        });



        btnHome.setOnClickListener(view -> {
            startActivity(new Intent(GameOverActivity.this, MenuActivity.class));
            finish();
        });
    }
}
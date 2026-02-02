package com.example.numera;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GameOverActivity extends AppCompatActivity {

    Button btnPlayAgain, btnHome;
    TextView score;
    EditText txtName;



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
        txtName = findViewById(R.id.editName);

        Intent intent = getIntent();
        String scored = intent.getStringExtra("score");
        int scores = Integer.parseInt(scored);

        score.setText(scored);

        btnPlayAgain.setOnClickListener(view -> {
            String playerName = txtName.getText().toString().trim();
            if (playerName.isEmpty()) playerName = "Player"; // default

            LeaderboardManager.saveScore(this, playerName, scores);

            startActivity(new Intent(this, GameActivity.class));
            finish();
        });

        btnHome.setOnClickListener(view -> {
            String playerName = txtName.getText().toString().trim();
            if (playerName.isEmpty()) playerName = "Player"; // default

            LeaderboardManager.saveScore(this, playerName, scores);

            startActivity(new Intent(this, MenuActivity.class));
            finish();
        });

    }
}
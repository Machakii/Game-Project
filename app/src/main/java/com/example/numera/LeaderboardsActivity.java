package com.example.numera;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;
import java.util.List;

public class LeaderboardsActivity extends AppCompatActivity {

    TextView txtScore1, txtScore2, txtScore3, txtPlayer1, txtPlayer2, txtPlayer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_leaderboards);


        Button bckBtn = findViewById(R.id.btnBack);
        bckBtn.setOnClickListener(v -> {
            SFXManager.click();
            startActivity(new Intent(this, MenuActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        });

        txtScore1 = findViewById(R.id.score1);
        txtScore2 = findViewById(R.id.score2);
        txtScore3 = findViewById(R.id.score3);
        txtPlayer1 = findViewById(R.id.player1);
        txtPlayer2 = findViewById(R.id.player2);
        txtPlayer3 = findViewById(R.id.player3);

        List<Integer> scores = LeaderboardManager.getScores(this);
        List<String> names = LeaderboardManager.getNames(this);

        if (scores.size() > 0) {
            txtScore1.setText(scores.get(0) + "pts");
            txtPlayer1.setText(names.get(0));
        }
        if (scores.size() > 1) {
            txtScore2.setText(scores.get(1) + "pts");
            txtPlayer2.setText(names.get(1));
        }
        if (scores.size() > 2) {
            txtScore3.setText(scores.get(2) + "pts");
            txtPlayer3.setText(names.get(2));
        }










    }
}
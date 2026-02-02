package com.example.numera;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerView;

public class MenuActivity extends AppCompatActivity {



    public Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_menu);
        SFXManager.init(this);




        Button startBtn = findViewById(R.id.btnPlay);
        Button leaderBtn = findViewById(R.id.btnLeaderboards);
        Button helpBtn = findViewById(R.id.btnHelp);

        startBtn.setOnClickListener(v -> {
            SFXManager.click();
            MusicManager.stop();
            MusicManager.start(MenuActivity.this, R.raw.bg_game);
            Intent intent = new Intent(MenuActivity.this, GameActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        });

        leaderBtn.setOnClickListener(v -> {
            SFXManager.click();
            Intent intent = new Intent(MenuActivity.this, LeaderboardsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        });

        helpBtn.setOnClickListener(v -> {
            SFXManager.click();
            Intent intent = new Intent(MenuActivity.this, HowtoPlay.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        });

    }

}
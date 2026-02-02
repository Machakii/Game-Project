package com.example.numera;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicManager {

    private static MediaPlayer player;

    // Start music (only if nothing is playing)
    public static void start(Context context, int resId) {
        if (player == null) {
            player = MediaPlayer.create(context.getApplicationContext(), resId);
            player.setLooping(true);
            player.start();
        } else if (!player.isPlaying()) {
            player.start();
        }
    }

    // Stop music completely
    public static void stop() {
        if (player != null) {
            if (player.isPlaying()) player.stop();
            player.release();
            player = null;
        }
    }

    // Pause music temporarily
    public static void pause() {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    // Resume music if paused
    public static void resume() {
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }

    // Check if music is playing
    public static boolean isPlaying() {
        return player != null && player.isPlaying();
    }
}



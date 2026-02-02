package com.example.numera;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;

public class SFXManager {

    private static SoundPool soundPool;
    private static int click, correct, wrong, changeDifficulty;

    public static void init(Context context) {
        if (soundPool != null) return;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(attributes)
                    .build();
        } else {
            soundPool = new SoundPool(6, android.media.AudioManager.STREAM_MUSIC, 0);
        }

        click = soundPool.load(context, R.raw.click, 1);
//        correct = soundPool.load(context, R.raw.correct, 1);
        wrong = soundPool.load(context, R.raw.wrong, 1);
        changeDifficulty = soundPool.load(context, R.raw.diff_change, 1);
    }

    private static void play(int sound) {
        if (soundPool != null) {
            soundPool.play(sound, 1f, 1f, 1, 0, 1f);
        }
    }

    public static void click()    { play(click); }
    public static void correct()  { play(correct); }
    public static void wrong()    { play(wrong); }
    public static void changeDifficulty() { play(changeDifficulty); }

    public static void release() {
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}

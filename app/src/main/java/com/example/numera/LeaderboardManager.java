package com.example.numera;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeaderboardManager {

    private static final String PREF_NAME = "leaderboard_prefs";
    private static final String KEY_NAMES = "names";
    private static final String KEY_SCORES = "scores";

    // Save a new score
    public static void saveScore(Context context, String playerName, int newScore) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        List<PlayerScore> leaderboard = getLeaderboard(context); // Get existing + defaults

        // Add new score
        leaderboard.add(new PlayerScore(playerName, newScore));

        // Sort descending by score
        Collections.sort(leaderboard);

        // Keep top 10
        if (leaderboard.size() > 10) {
            leaderboard = leaderboard.subList(0, 10);
        }

        // Save back to SharedPreferences
        StringBuilder sbScores = new StringBuilder();
        StringBuilder sbNames = new StringBuilder();
        for (PlayerScore ps : leaderboard) {
            sbScores.append(ps.score).append(",");
            sbNames.append(ps.name).append(",");
        }

        prefs.edit()
                .putString(KEY_SCORES, sbScores.toString())
                .putString(KEY_NAMES, sbNames.toString())
                .apply();
    }

    // Get scores as integers
    public static List<Integer> getScores(Context context) {
        List<PlayerScore> leaderboard = getLeaderboard(context);
        List<Integer> scores = new ArrayList<>();
        for (PlayerScore ps : leaderboard) {
            scores.add(ps.score);
        }
        return scores;
    }

    // Get names
    public static List<String> getNames(Context context) {
        List<PlayerScore> leaderboard = getLeaderboard(context);
        List<String> names = new ArrayList<>();
        for (PlayerScore ps : leaderboard) {
            names.add(ps.name);
        }
        return names;
    }

    // Get full leaderboard (used internally)
    private static List<PlayerScore> getLeaderboard(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        String savedScores = prefs.getString(KEY_SCORES, "");
        String savedNames = prefs.getString(KEY_NAMES, "");

        List<PlayerScore> leaderboard = new ArrayList<>();

        if (!savedScores.isEmpty() && !savedNames.isEmpty()) {
            String[] scoresArr = savedScores.split(",");
            String[] namesArr = savedNames.split(",");
            int len = Math.min(scoresArr.length, namesArr.length);
            for (int i = 0; i < len; i++) {
                leaderboard.add(new PlayerScore(namesArr[i], Integer.parseInt(scoresArr[i])));
            }
        } else {
            // Load default static leaderboard if nothing saved yet
            leaderboard.add(new PlayerScore("PLAYER 1", 100));
            leaderboard.add(new PlayerScore("PLAYER 2", 80));
            leaderboard.add(new PlayerScore("PLAYER 3", 30));
        }

        // Ensure sorted descending
        Collections.sort(leaderboard);
        return leaderboard;
    }
}

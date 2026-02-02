package com.example.numera;

public class PlayerScore implements Comparable<PlayerScore> {
    String name;
    int score;

    PlayerScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(PlayerScore other) {
        // Sort descending by score
        return Integer.compare(other.score, this.score);
    }
}


package com.devtrack.dto;

public class StreakResponse {

    private int streak;

    public StreakResponse(int streak) {
        this.streak = streak;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }
}
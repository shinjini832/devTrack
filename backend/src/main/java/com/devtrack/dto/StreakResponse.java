package com.devtrack.dto;

public class StreakResponse {

    private int currentstreak;

    public StreakResponse(int currentstreak) {
        this.currentstreak = currentstreak;
    }

    public int getCurrentstreak() {
        return currentstreak;
    }

    public void setCurrentstreak(int streak) {
        this.currentstreak = currentstreak;
    }
}
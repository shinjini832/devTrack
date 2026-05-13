package com.devtrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StreakResponse {

    public StreakResponse(int currentStreak) {
		super();
		this.currentStreak = currentStreak;
	}

	private int currentStreak;

	public int getCurrentStreak() {
		return currentStreak;
	}

	public void setCurrentStreak(int currentStreak) {
		this.currentStreak = currentStreak;
	}
}
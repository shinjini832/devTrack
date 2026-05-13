package com.devtrack.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ActivityLogRequest {

    private Long userId;
    private LocalDate date;
    private int hoursCoded;
    private int tasksCompleted;
    private String notes;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getHoursCoded() {
		return hoursCoded;
	}
	public void setHoursCoded(int hoursCoded) {
		this.hoursCoded = hoursCoded;
	}
	public int getTasksCompleted() {
		return tasksCompleted;
	}
	public void setTasksCompleted(int tasksCompleted) {
		this.tasksCompleted = tasksCompleted;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
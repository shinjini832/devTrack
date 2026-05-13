package com.devtrack.dto;

public class WeeklyAnalyticsResponse {
	 private int totalHours;
	    private int totalTasks;
	    private double averageHours;
		public int getTotalHours() {
			return totalHours;
		}
		public WeeklyAnalyticsResponse(int totalHours, int totalTasks, double averageHours) {
			super();
			this.totalHours = totalHours;
			this.totalTasks = totalTasks;
			this.averageHours = averageHours;
		}
		public void setTotalHours(int totalHours) {
			this.totalHours = totalHours;
		}
		public int getTotalTasks() {
			return totalTasks;
		}
		public void setTotalTasks(int totalTasks) {
			this.totalTasks = totalTasks;
		}
		public double getAverageHours() {
			return averageHours;
		}
		public void setAverageHours(double averageHours) {
			this.averageHours = averageHours;
		}
}

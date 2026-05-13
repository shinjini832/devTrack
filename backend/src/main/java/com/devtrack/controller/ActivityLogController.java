package com.devtrack.controller;

import com.devtrack.dto.ActivityLogRequest;
import com.devtrack.dto.LeaderboardResponse;
import com.devtrack.dto.StreakResponse;
import com.devtrack.dto.WeeklyAnalyticsResponse;
import com.devtrack.model.ActivityLog;
import com.devtrack.service.ActivityLogService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/logs")
public class ActivityLogController {

    @Autowired
    private ActivityLogService activityLogService;

    @PostMapping
    public ActivityLog addLog(@RequestBody ActivityLogRequest request) {
        return activityLogService.addLog(request);
    }
    
    @GetMapping("/user/{userId}")
    public List<ActivityLog> getLogs(@PathVariable Long userId) {
        return activityLogService.getLogsByUser(userId);
    }
    
    @GetMapping("/streak/{userId}")
    public StreakResponse getStreak(@PathVariable Long userId) {
        return activityLogService.calculateStreak(userId);
    }
    
    @GetMapping("/weekly/{userId}")
    public WeeklyAnalyticsResponse getWeeklyAnalytics(
            @PathVariable Long userId) {

        return activityLogService.getWeeklyAnalytics(userId);
    }
    
    @GetMapping("/leaderboard")
    public List<LeaderboardResponse> getLeaderboard() {

        return activityLogService.getLeaderboard();
    }
}
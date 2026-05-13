package com.devtrack.service;

import com.devtrack.dto.ActivityLogRequest;
import com.devtrack.dto.LeaderboardResponse;
import com.devtrack.dto.StreakResponse;
import com.devtrack.dto.WeeklyAnalyticsResponse;
import com.devtrack.model.ActivityLog;
import com.devtrack.model.User;
import com.devtrack.repository.ActivityLogRepository;
import com.devtrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class ActivityLogService {

    @Autowired
    private ActivityLogRepository activityLogRepository;

    @Autowired
    private UserRepository userRepository;
    
    public List<ActivityLog> getLogsByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return activityLogRepository.findByUserOrderByDateDesc(user);
    }
    
    
    public List<LeaderboardResponse> getLeaderboard() {

        List<User> users = userRepository.findAll();

        List<LeaderboardResponse> leaderboard = new ArrayList<>();

        for (User user : users) {

            List<ActivityLog> logs =
                    activityLogRepository.findByUser(user);

            int score = 0;

            for (ActivityLog log : logs) {

                score += (log.getHoursCoded() * 2)
                        + (log.getTasksCompleted() * 5);
            }

            leaderboard.add(
                    new LeaderboardResponse(user.getName(), score)
            );
        }

        // Sort descending by score
        leaderboard.sort((a, b) -> b.getScore() - a.getScore());

        return leaderboard;
    }
    
    public WeeklyAnalyticsResponse getWeeklyAnalytics(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);

        List<ActivityLog> logs =
                activityLogRepository.findByUserAndDateAfter(user, sevenDaysAgo);

        int totalHours = 0;
        int totalTasks = 0;

        for (ActivityLog log : logs) {

            totalHours += log.getHoursCoded();
            totalTasks += log.getTasksCompleted();
        }

        double averageHours = logs.isEmpty()
                ? 0
                : (double) totalHours / logs.size();

        return new WeeklyAnalyticsResponse(
                totalHours,
                totalTasks,
                averageHours
        );
    }
    public StreakResponse calculateStreak(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<ActivityLog> logs =
                activityLogRepository.findByUserOrderByDateDesc(user);

        if (logs.isEmpty()) {
            return new StreakResponse(0);
        }

        int streak = 1;

        for (int i = 0; i < logs.size() - 1; i++) {

            LocalDate currentDate = logs.get(i).getDate();
            LocalDate nextDate = logs.get(i + 1).getDate();

            // Check if dates are consecutive
            if (currentDate.minusDays(1).equals(nextDate)) {
                streak++;
            } else {
                break;
            }
        }

        return new StreakResponse(streak);
    }

    public ActivityLog addLog(ActivityLogRequest request) {

        // Step 1: Find user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 2: Create log
        ActivityLog log = new ActivityLog();
        log.setDate(request.getDate());
        log.setHoursCoded(request.getHoursCoded());
        log.setTasksCompleted(request.getTasksCompleted());
        log.setNotes(request.getNotes());
        log.setUser(user); // 🔥 linking user

        // Step 3: Save
        return activityLogRepository.save(log);
        
       
    }
}
package com.devtrack.repository;

import com.devtrack.model.ActivityLog;
import com.devtrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    List<ActivityLog> findByUser(User user);

    Optional<ActivityLog> findByUserAndDate(User user, LocalDate date);
    
    List<ActivityLog> findByUserOrderByDateDesc(User user);
    
    List<ActivityLog> findByUserAndDateAfter(User user, LocalDate date);
}

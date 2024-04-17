package com.whotere.rationplanner.data.repository;

import com.whotere.rationplanner.data.model.PlannedDay;
import com.whotere.rationplanner.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PlannedDayRepository extends JpaRepository<PlannedDay, String> {

    Set<PlannedDay> findByUser(User user);

    boolean existsByIdAndUser(String id, User user);
}

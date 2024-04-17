package com.whotere.rationplanner.data.repository;

import com.whotere.rationplanner.data.model.Schedule;
import com.whotere.rationplanner.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ScheduleRepository extends JpaRepository<Schedule, String> {

    @Query(
        value = "select s from Schedule s where s.plannedDay.user.id = :#{#user.id}"
    )
    Set<Schedule> findAllByUser(@Param("user") User user);
}

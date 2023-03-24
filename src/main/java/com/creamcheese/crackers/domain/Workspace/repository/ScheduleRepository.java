package com.creamcheese.crackers.domain.Workspace.repository;

import com.creamcheese.crackers.domain.Workspace.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}

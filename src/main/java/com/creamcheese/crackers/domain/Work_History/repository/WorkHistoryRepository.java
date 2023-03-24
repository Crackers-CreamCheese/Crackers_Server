package com.creamcheese.crackers.domain.Work_History.repository;

import com.creamcheese.crackers.domain.Work_History.entity.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Integer> {
	List<WorkHistory> findByWorkspaceId(Integer workspaceId);
}

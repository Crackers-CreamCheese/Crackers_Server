package com.creamcheese.crackers.domain.Work_History;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Integer> {
	List<WorkHistory> findByWorkspaceId(Integer workspaceId);

	WorkHistory findByHistoryId(Integer historyId);

	boolean existsByHistoryId(Integer historyId);

	Optional<WorkHistory> findById(Integer workHistoryId);
}

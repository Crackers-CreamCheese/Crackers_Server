package com.creamcheese.crackers.domain.Workspace;

import com.creamcheese.crackers.domain.Account.Account;
import com.creamcheese.crackers.domain.Workspace.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkspaceRepository extends JpaRepository<Workspace, Integer> {
	@Override
	Optional<Workspace> findById(Integer integer);

	Optional<Workspace> findByName(String name);

	List<Workspace> findByAccount(Account account);

}

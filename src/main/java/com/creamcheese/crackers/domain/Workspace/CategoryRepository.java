package com.creamcheese.crackers.domain.Workspace;

import com.creamcheese.crackers.domain.Workspace.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Override
	Optional<Category> findById(Integer integer);
}

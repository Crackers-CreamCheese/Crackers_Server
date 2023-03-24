package com.creamcheese.crackers.domain.Account.repository;

import com.creamcheese.crackers.domain.Account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findById(Integer id);

	Optional<Account> findByLoginId(String loginId);

	Boolean existsByLoginId(String loginId);

	Optional<Account> findByNickname(String nickname);
}

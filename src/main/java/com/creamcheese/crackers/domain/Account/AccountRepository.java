package com.creamcheese.crackers.domain.Account;

import com.creamcheese.crackers.domain.Account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findById(Integer id);

	Optional<Account> findByLoginId(String loginId);

	Boolean existsByLoginId(String loginId);

	Optional<Account> findByNickname(String nickname);
}

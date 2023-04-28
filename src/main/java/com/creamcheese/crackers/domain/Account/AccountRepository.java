package com.creamcheese.crackers.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findById(Integer id);

	Account findByLoginId(String loginId);

//	Optional<Account> findByLoginIdAndEncodedPassword(String loginId, String encodedPassword);

	Boolean existsByLoginId(String loginId);

	Optional<Account> findByNickname(String nickname);
}

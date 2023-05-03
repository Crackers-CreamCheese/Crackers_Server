package com.creamcheese.crackers.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
//	Optional<Account> findById(Integer id);

	Account findByLoginId(String loginId);

//	Optional<Account> findByLoginIdAndEncodedPassword(String loginId, String encodedPassword);

	Boolean existsByLoginId(String loginId);

	Optional<Account> findByNickname(String nickname);
}

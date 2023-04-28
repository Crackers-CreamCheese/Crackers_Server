package com.creamcheese.crackers.domain.card;

import com.creamcheese.crackers.domain.Account.Account;
import com.creamcheese.crackers.domain.Work_History.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCardRepository extends JpaRepository<UserCard, Integer> {

    Optional<UserCard> findByWorkHistory(WorkHistory workHistory);

    List<UserCard> findByAccount(Account account);
}

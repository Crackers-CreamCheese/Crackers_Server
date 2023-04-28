package com.creamcheese.crackers.domain.card;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {

    Card findByCardId(Integer cardId);
}

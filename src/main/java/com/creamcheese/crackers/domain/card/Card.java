package com.creamcheese.crackers.domain.card;

import com.creamcheese.crackers.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id", updatable = false)
    private Integer cardId;

    @NotNull
    @Column(name = "prev")
    private String prev;

    @NotNull
    @Column(name = "next")
    private String next;

    @NotNull
    @Column(name = "unit")
    private String unit;

    @NotNull
    @Column(name = "per1min")
    private Double per1min;

    @Column(name = "round")
    private Boolean round;

    @Builder
    public Card(String prev, String next, String unit, Double per1min, Boolean round){
        this.prev = prev;
        this.next = next;
        this.unit = unit;
        this.per1min = per1min;
        this.round = round;
    }
}

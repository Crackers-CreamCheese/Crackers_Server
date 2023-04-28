package com.creamcheese.crackers.dto.card;

import com.creamcheese.crackers.domain.card.Card;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardGenerateDTO {

    @NotNull
    private String prev;

    @NotNull
    private String next;

    @NotNull
    private String unit;

    @NotNull
    private Double per1min;

    private Boolean round;

    @Builder
    public CardGenerateDTO(String prev, String next, String unit, Double per1min, Boolean round){
        this.prev = prev;
        this.next = next;
        this.unit = unit;
        this.per1min = per1min;
        this.round = round;
    }

    public Card toEntity(){
        return Card.builder()
                .prev(this.prev)
                .next(this.next)
                .unit(this.unit)
                .per1min(this.per1min)
                .round(this.round)
                .build();
    }
}


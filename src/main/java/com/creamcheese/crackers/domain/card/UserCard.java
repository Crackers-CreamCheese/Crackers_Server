package com.creamcheese.crackers.domain.card;

import com.creamcheese.crackers.domain.Account.Account;
import com.creamcheese.crackers.domain.Work_History.WorkHistory;
import com.creamcheese.crackers.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class UserCard extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usercard_id")
    private Integer userCardId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "history_id")
    private WorkHistory workHistory;

    @Builder
    public UserCard(Account account, Card card, WorkHistory workHistory){
        this.account = account;
        this.card = card;
        this.workHistory = workHistory;
    }
}

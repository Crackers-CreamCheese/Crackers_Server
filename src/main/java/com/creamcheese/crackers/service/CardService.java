package com.creamcheese.crackers.service;

import com.creamcheese.crackers.domain.Account.Account;
import com.creamcheese.crackers.domain.Work_History.WorkHistory;
import com.creamcheese.crackers.domain.Work_History.WorkHistoryRepository;
import com.creamcheese.crackers.domain.card.Card;
import com.creamcheese.crackers.domain.card.CardRepository;
import com.creamcheese.crackers.domain.card.UserCard;
import com.creamcheese.crackers.domain.card.UserCardRepository;
import com.creamcheese.crackers.dto.card.CardGenerateDTO;
import com.creamcheese.crackers.exception.CustomException.UserCardAlreadyExists;
import com.creamcheese.crackers.exception.CustomException.UserCardNotFoundException;
import com.creamcheese.crackers.exception.CustomException.WorkHistoryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserCardRepository userCardRepository;

//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private WorkspaceRepository workspaceRepository;
//
    @Autowired
    private WorkHistoryRepository workHistoryRepository;

    //유저카드 생성
    @Transactional
    public String generateUserCard(Account account, Integer workHistoryId, Integer randNum){
        WorkHistory workHistory = workHistoryRepository.findById(workHistoryId)
                .orElseThrow(WorkHistoryNotFoundException::new);

        if(userCardRepository.existsByWorkHistory(workHistory)){
            throw new UserCardAlreadyExists();
        }

        Card card = cardRepository.findByCardId(randNum);
        UserCard userCard = new UserCard(account, card, workHistory);
        userCardRepository.save(userCard);
        return generateSentence(workHistory, card);
    }

    //카드 개별조회
    @Transactional
    public String findUserCard(Integer historyId){
        WorkHistory workHistory = workHistoryRepository.findByHistoryId(historyId);
        UserCard userCard = userCardRepository.findByWorkHistory(workHistory)
                .orElseThrow(UserCardNotFoundException::new);
        return generateSentence(workHistory, userCard.getCard());
    }

    //카드 목록전체 조회
    @Transactional
    public List<String> findAllUserCard(Account account){
        List<String> allSentences = new ArrayList<>();
        List<UserCard> userCards = userCardRepository.findByAccount(account);
        for(UserCard userCard : userCards){
            allSentences.add(generateSentence(userCard.getWorkHistory(), userCard.getCard()));
        }
        return allSentences;
    }

    //문장 계산
    @Transactional
    public String generateSentence(WorkHistory workHistory, Card card){
        Integer start = workHistory.getStartTime();
        Integer end = workHistory.getEndTime();

        int hour2min = ((end/100) - (start/100))*60;
        Integer min = (end%100) - (start%100) + hour2min;

        double calc = min*card.getPer1min();
        if(card.getRound()){
            calc = Math.floor(calc);
        }
        return card.getPrev()+" "+calc+card.getUnit()+" "+card.getNext();
    }

    //관리자) 새로운 종류의 카드(단위) 추가
    @Transactional
    public Card createCard(CardGenerateDTO cardGenerateDTO){
//        Card card = new Card(cardGenerateDTO.getPrev(), cardGenerateDTO.getNext(), cardGenerateDTO.getUnit(), cardGenerateDTO.getPer1min(), cardGenerateDTO.getRound());
        Card card = cardGenerateDTO.toEntity();
        log.info(card.getPrev());
        cardRepository.save(card);
        return card;
    }
}

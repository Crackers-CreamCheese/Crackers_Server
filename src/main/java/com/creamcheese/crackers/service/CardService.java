package com.creamcheese.crackers.service;

import com.creamcheese.crackers.domain.Account.Account;
import com.creamcheese.crackers.domain.Account.AccountRepository;
import com.creamcheese.crackers.domain.Work_History.WorkHistory;
import com.creamcheese.crackers.domain.Work_History.WorkHistoryRepository;
import com.creamcheese.crackers.domain.Workspace.Workspace;
import com.creamcheese.crackers.domain.Workspace.WorkspaceRepository;
import com.creamcheese.crackers.domain.card.Card;
import com.creamcheese.crackers.domain.card.CardRepository;
import com.creamcheese.crackers.domain.card.UserCard;
import com.creamcheese.crackers.domain.card.UserCardRepository;
import com.creamcheese.crackers.dto.card.CardGenerateDTO;
import com.creamcheese.crackers.exception.CustomException.UserCardNotFoundException;
import com.creamcheese.crackers.exception.CustomException.WorkHistoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private CardRepository cardRepository;
    private UserCardRepository userCardRepository;
    private AccountRepository accountRepository;
    private WorkspaceRepository workspaceRepository;
    private WorkHistoryRepository workHistoryRepository;

    //유저카드 생성
    @Transactional
    public String generateUserCard(Account account, Integer workHistoryId, Integer randNum){
        Card card = cardRepository.findByCardId(randNum);
        WorkHistory workHistory = workHistoryRepository.findById(workHistoryId)
                .orElseThrow(WorkHistoryNotFoundException::new);
        UserCard userCard = new UserCard(account, card, workHistory);
        userCardRepository.save(userCard);
        return generateSentence(workHistory, card);
    }

    //카드 개별조회
    @Transactional
    public String findUserCard(WorkHistory workHistory){
        UserCard userCard = userCardRepository.findByWorkHistory(workHistory)
                .orElseThrow(UserCardNotFoundException::new);
        return generateSentence(workHistory, userCard.getCard());
    }

    //카드 목록전체 조회
    @Transactional
    public List<String> findAllUserCard(Account account){
        List<String> allSentences = new ArrayList<String>();
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

//    //관리자) 새로운 종류의 카드(단위) 추가
//    @Transactional
//    public void createCard(CardGenerateDTO cardGenerateDTO){
//        Card card = new Card(cardGenerateDTO.toEntity());
//        cardRepository.save(card);
//    }
}

package com.creamcheese.crackers.controller;

import com.creamcheese.crackers.domain.Work_History.WorkHistory;
import com.creamcheese.crackers.domain.card.Card;
import com.creamcheese.crackers.dto.card.CardGenerateDTO;
import com.creamcheese.crackers.dto.card.CreateCardReqDTO;
import com.creamcheese.crackers.jwt.PrincipalDetails;
import com.creamcheese.crackers.service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/card")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) { this.cardService = cardService; }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<String> createUserCard(
            @RequestBody CreateCardReqDTO createCardReqDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails){
        String sentence = cardService.generateUserCard(
                principalDetails.getAccount(), createCardReqDTO.getWorkHistoryId(), createCardReqDTO.getRandNum());

        return ResponseEntity.ok()
                .body(sentence);
    }

    @GetMapping("")
    public ResponseEntity<String> findUserCard(WorkHistory workHistory){
        return ResponseEntity.ok()
                .body(cardService.findUserCard(workHistory));
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> findAllUserCard(@AuthenticationPrincipal PrincipalDetails principalDetails){
        return ResponseEntity.ok()
                .body(cardService.findAllUserCard(principalDetails.getAccount()));
    }
}

package com.fintech.jjeondaproject.controller;

import com.fintech.jjeondaproject.dto.card.CardDto;
import com.fintech.jjeondaproject.dto.card.CardListDto;
import com.fintech.jjeondaproject.service.CardTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cardTest")
public class CardController {
    private  final CardTestService cardTestService;

    //전체 카드 리스트 조회
    @GetMapping("/list")
    public String cardList(Model model){
        List<CardListDto> cardListDto = cardTestService.selectAll();
        model.addAttribute("cardList", cardListDto);

//        System.out.println(cardListDto);
        return "cardTest";
    }

    // 로그인한 유저의 카드 리스트 조회

    // 로그인한 유저의 카드사별 카드 리스트 조회
    @GetMapping("/list/{bankId}")
    public String cardListByBankId(@PathVariable("bankId") String cardId, Model model){
        List<CardListDto> cardListDto = cardTestService.cardListByBankId();
        model.addAttribute("cardList", cardListDto);

//        System.out.println(cardListDto);
        return "cardTest";
    }

    // 카드 상세페이지 조회
    @GetMapping("/list/{cardId}")
    public String cardDetail(@PathVariable("cardId") String cardId, Model model){
        CardDto cardDto = cardTestService.selectOneByCardId(Long.parseLong(cardId));
        model.addAttribute("cardDetail", cardDto);
        return "cardDetail";
    }
}
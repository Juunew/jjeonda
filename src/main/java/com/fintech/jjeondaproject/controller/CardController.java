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
@RequestMapping("/cards")
public class CardController {
    private  final CardTestService cardTestService;

    @GetMapping("/test")
    public String testing(){
        return "test";
    }
    @GetMapping
    public String home(){

        return "card/cardIndex";
    }

    //전체 카드 리스트 조회
    @GetMapping("/list")
    public String cardList(Model model){
        List<CardListDto> cardListDto = cardTestService.selectAll();
        model.addAttribute("cardList", cardListDto);
//        System.out.println(cardListDto);
        return "card/cardList";
    }

    //    카드사 별 보유 카드 목록
//    @GetMapping("/list?bankId=")
//    public String cardListByBankId(@RequestParam("bankId") String bankId, Model model){
//        List<CardListDto> cardListDto = cardTestService.cardListByBankId();
//        model.addAttribute("cardList", cardListDto);
//
////        System.out.println(cardListDto);
//        return "cardTest";
//    }

    //    카드 별 상세 조회
    @GetMapping("/detail/{cardId}")
    public String cardDetail(@PathVariable("cardId") String cardId, Model model){
        CardDto cardDto = cardTestService.selectOneByCardId(Long.parseLong(cardId));
        model.addAttribute("cardDetail", cardDto);
        return "card/cardDetail";
    }

    //    카드 별명 설정 페이지
//    @PutMapping("/{cardId}/nickname”)
}
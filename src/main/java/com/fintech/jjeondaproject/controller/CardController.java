package com.fintech.jjeondaproject.controller;

import com.fintech.jjeondaproject.dto.card.CardDto;
import com.fintech.jjeondaproject.dto.card.CardListDto;
import com.fintech.jjeondaproject.dto.card.CardTestDto;
import com.fintech.jjeondaproject.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private  final CardService cardService;

    //전체 카드 리스트 조회
    @GetMapping("/list")
    public String cardList(Model model){
        List<CardListDto> cardListDto = cardService.cardList();
        model.addAttribute("cardList", cardListDto);
        System.out.println(cardListDto);
        return "card/cardList";
    }

    //    카드사 별 보유 카드 목록
    @GetMapping("/list/{bankId}")
    public String cardList(@PathVariable Long bankId, Model model){
        List<CardListDto> cardListDto = cardService.cardListByBankId(bankId);
        model.addAttribute("cardList", cardListDto);
        System.out.println(cardListDto);
        return "card/cardList";
    }

    //    카드 별 상세 조회
    @GetMapping("/detail/{cardId}")
    public String cardDetail(@PathVariable Long cardId, Model model){
        CardDto cardDto = cardService.selectOneByCardId(cardId);
        model.addAttribute("cardDetail", cardDto);
        System.out.println(cardDto);
        return "card/cardDetail";
    }

    @GetMapping("/edit/{cardId}")
    public String cardEditScreen(@PathVariable Long cardId, Model model){
        CardDto cardDto = cardService.selectOneByCardId(cardId);
        model.addAttribute("cardDetail", cardDto);
        System.out.println(cardDto);
        return "card/cardEdit";
    }

    // 카드 별명 설정 페이지
    @PutMapping("/{cardId}/nickname")
    public String EditNickname(@PathVariable Long cardId, @RequestBody CardTestDto.test data, Model model){
        CardDto cardDto = cardService.changeNickname(cardId, data.getNickName());
        model.addAttribute("cardDetail", cardDto);
        System.out.println(cardDto);
        return "card/cardDetail";
    }
}
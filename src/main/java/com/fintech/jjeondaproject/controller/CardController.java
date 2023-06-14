package com.fintech.jjeondaproject.controller;

import com.fintech.jjeondaproject.common.UserInfo;
import com.fintech.jjeondaproject.config.annotation.InfoUser;
import com.fintech.jjeondaproject.dto.card.CardModDto;
import com.fintech.jjeondaproject.util.jwt.JwtProvider;
import com.fintech.jjeondaproject.dto.card.CardDto;
import com.fintech.jjeondaproject.dto.card.CardListDto;
import com.fintech.jjeondaproject.service.OpenBankingService;
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
    public String cardList(@InfoUser UserInfo userInfo, Model model){
        List<CardListDto> cardListDto = cardService.cardListByUserId(userInfo.getUserId());
        List<CardListDto> category = cardService.cardList();
        model.addAttribute("cardList", cardListDto);
        model.addAttribute("category", category);
        return "card/cardList";
    }

    //    카드사 별 보유 카드 목록
    @GetMapping("/list/bank/{bankId}")
    public String cardList(@InfoUser UserInfo userInfo, @PathVariable Long bankId, Model model){
        List<CardListDto> cardListDto = cardService.cardListByUserIdAndBankId(userInfo.getUserId(), bankId);
        List<CardListDto> category = cardService.cardList();
        model.addAttribute("cardList", cardListDto);
        model.addAttribute("category", category);
        return "card/cardList";
    }

    //    카드 별 상세 조회
    @GetMapping("/detail/{cardId}")
    public String cardDetail(@PathVariable Long cardId, Model model){
        CardDto cardDto = cardService.selectOneByCardId(cardId);
        model.addAttribute("cardDetail", cardDto);
        return "card/cardDetail";
    }

    //카드 별명 수정 페이지
    @GetMapping("/edit/{cardId}")
    public String cardEditScreen(@PathVariable Long cardId, Model model){
        CardDto cardDto = cardService.selectOneByCardId(cardId);
        model.addAttribute("cardDetail", cardDto);
        return "card/cardEdit";
    }

    // 카드 별명 수정 저장 페이지
    @PutMapping("/{cardId}/nickname")
    public String EditNickname(@PathVariable Long cardId, @RequestBody CardModDto data, Model model){ // @RequestParam("cardName") String data, Model model){
        CardDto cardDto = cardService.changeNickname(cardId, data.getCardName());
        model.addAttribute("cardDetail", cardDto);
        return "card/cardDetail";
    }
}
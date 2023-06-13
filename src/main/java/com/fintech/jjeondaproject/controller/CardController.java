package com.fintech.jjeondaproject.controller;

import com.fintech.jjeondaproject.util.jwt.JwtProvider;
import com.fintech.jjeondaproject.dto.card.CardDto;
import com.fintech.jjeondaproject.dto.card.CardListDto;
//import com.fintech.jjeondaproject.feign.BankingFeign;
import com.fintech.jjeondaproject.service.OpenBankingService;
import com.fintech.jjeondaproject.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private  final CardService cardService;
    private final JwtProvider jwtProvider;

//    private final BankingFeign bankingFeign;
    private final OpenBankingService bankingService;

    //전체 카드 리스트 조회
    @GetMapping("/list")
    public String cardList(Model model, HttpServletRequest request){// , HttpServletRequest request){

        String token = jwtProvider.getJwtFromCookie(request);
        Long id = jwtProvider.getClaims(token).get("UserId", Long.class);

        List<CardListDto> cardListDto = cardService.cardListByUserId(id);
        List<CardListDto> category = cardService.cardList();
        model.addAttribute("cardList", cardListDto);
        model.addAttribute("category", category);
        return "card/cardList";
    }
    //(로그인한 유저) 전체 카드 리스트 조회 --> 이제 안씀
    @GetMapping("/list/user/{userId}")
    public String cardListByUserId(@PathVariable Long userId, Model model){
        List<CardListDto> result = cardService.cardListByUserId(userId);
        List<CardListDto> category = cardService.cardList();
        model.addAttribute("cardList", result);
        model.addAttribute("category", category);
        return "card/cardList";
    }

    //    카드사 별 보유 카드 목록
    @GetMapping("/list/bank/{bankId}")
    public String cardList(@PathVariable Long bankId, Model model, HttpServletRequest request){

        String token = jwtProvider.getJwtFromCookie(request);
        Long id = jwtProvider.getClaims(token).get("UserId", Long.class);

        List<CardListDto> cardListDto = cardService.cardListByUserIdAndBankId(id, bankId);
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

    @GetMapping("/edit/{cardId}")
    public String cardEditScreen(@PathVariable Long cardId, Model model){
        CardDto cardDto = cardService.selectOneByCardId(cardId);
        model.addAttribute("cardDetail", cardDto);
        return "card/cardEdit";
    }

    // 카드 별명 설정 페이지
    @PutMapping("/{cardId}/nickname")
    public String EditNickname(@PathVariable Long cardId, @RequestParam("cardName") String data, Model model){
        CardDto cardDto = cardService.changeNickname(cardId, data);
        model.addAttribute("cardDetail", cardDto);
        return "card/cardDetail";
    }
}
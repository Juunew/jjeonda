package com.fintech.jjeondaproject.apiController;

import com.fintech.jjeondaproject.common.response.ResBody;
import com.fintech.jjeondaproject.dto.card.CardListDto;
import com.fintech.jjeondaproject.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
@RestController
public class CardApiController {

    private final CardService cardService;

    //전체 카드 리스트 조회
    @GetMapping("/list")
    public ResBody<?> cardList(){
        List<CardListDto> result = cardService.cardList();
        System.out.println(result);
        return ResBody.success(result);
    }


}
package com.fintech.jjeondaproject.apiController;

import com.fintech.jjeondaproject.common.UserInfo;
import com.fintech.jjeondaproject.common.response.ResBody;
import com.fintech.jjeondaproject.config.annotation.InfoUser;
import com.fintech.jjeondaproject.dto.card.CardDto;
import com.fintech.jjeondaproject.dto.card.CardListDto;
import com.fintech.jjeondaproject.dto.card.CardModDto;
import com.fintech.jjeondaproject.service.OpenBankingService;
import com.fintech.jjeondaproject.service.CardService;
import com.fintech.jjeondaproject.util.jwt.JwtProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cards", description = "카드 관리 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
@RestController
public class CardApiController {

    private final CardService cardService;

    //전체 카드 리스트 조회
    @GetMapping("/list")
    public ResBody<?> cardList(@InfoUser UserInfo userInfo){
        List<CardListDto> result = cardService.cardListByUserId(userInfo.getUserId());
        return ResBody.success(result);
    }

    //    카드사 별 보유 카드 목록
    @GetMapping("/list/bank/{bankId}")
    public ResBody<?> cardList(@InfoUser UserInfo userInfo, @PathVariable Long bankId){
        List<CardListDto> cardListDto = cardService.cardListByUserIdAndBankId(userInfo.getUserId(), bankId);
        List<CardListDto> category = cardService.cardList();
        return ResBody.success(cardListDto);
    }

    //    카드 별 상세 조회
    @GetMapping("/detail/{cardId}")
    public ResBody<?> cardDetail(@PathVariable Long cardId){
        CardDto result = cardService.selectOneByCardId(cardId);
        return ResBody.success(result);
    }

    //카드 별멍 설정 페이지
    @GetMapping("/edit/{cardId}")
    public ResBody<?> cardEditScreen(@PathVariable Long cardId){
        CardDto result = cardService.selectOneByCardId(cardId);
        return ResBody.success(result);
    }

    // 카드 별명 완료 후 페이지
    @PutMapping("/{cardId}/nickname")
    public ResBody<?> EditNickname(@PathVariable Long cardId, @RequestBody CardModDto data){
        CardDto result = cardService.changeNickname(cardId, data.getCardName());
        return ResBody.success(result);
    }

}

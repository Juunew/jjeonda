package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.card.CardDto;
import com.fintech.jjeondaproject.dto.card.CardListDto;
import com.fintech.jjeondaproject.entity.CardEntity;
import com.fintech.jjeondaproject.entity.UserEntity;
import com.fintech.jjeondaproject.repository.CardTestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CardTestService {
    private final CardTestRepository cardTestRepository;

    public List<CardListDto> selectAll(){

        List<CardEntity> cardEntityList = cardTestRepository.findAll();
        return cardEntityList.stream().
                map(m->new CardListDto(
                        m.getCardId(),
                        m.getUser(),
                        m.getBank(),
                        m.getCardName(),
                        m.getSettlementDay(),
                        m.getSettlementDate(),
                        m.getPaymentAmt()
                        ))
                .collect(Collectors.toList());
    }

    public List<CardListDto> cardListByBankId(){

//        List<CardEntity> cardEntityList = cardTestRepository.findByBank();
//        return cardEntityList.stream().
//                map(m->new CardListDto(
//                        m.getCardId(),
//                        m.getUser(),
//                        m.getBank(),
//                        m.getCardName(),
//                        m.getSettlementDay(),
//                        m.getSettlementDate(),
//                        m.getPaymentAmt()
//                ))
//                .collect(Collectors.toList());
        return null;
    }



    public CardDto selectOneByCardId(long cardId){
        CardEntity cardEntity = cardTestRepository.findByCardId(cardId);
        CardDto cardDto = CardDto.builder()
                .cardId(cardEntity.getCardId())
                .user(cardEntity.getUser())
                .bank(cardEntity.getBank())
                .cardName(cardEntity.getCardName())
                .settlementDay(cardEntity.getSettlementDay())
                .settlementDate(cardEntity.getSettlementDate())
                .paymentAmt(cardEntity.getPaymentAmt())
                .build();
        return cardDto;
    }
}

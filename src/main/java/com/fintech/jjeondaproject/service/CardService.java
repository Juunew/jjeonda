package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.card.CardDto;
import com.fintech.jjeondaproject.dto.card.CardListDto;
import com.fintech.jjeondaproject.entity.CardEntity;
import com.fintech.jjeondaproject.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CardService {
    private final CardRepository cardRepository;
    public List<CardListDto> cardList(){
        List<CardEntity> cardEntityList = cardRepository.findAll();
        return cardEntityList.stream().
                map(m->new CardListDto(
                        m.getCardId(),
                        m.getUser().getId(),
                        m.getBank().getId(),
                        m.getCardName(),
                        m.getSettlementDay(),
                        m.getSettlementDate(),
                        m.getPaymentAmt()
                ))
                .collect(Collectors.toList());
    }

    public List<CardListDto> cardListByBankId(long bankId){

        List<CardEntity> cardEntityList = cardRepository.findByBankId(bankId);
        return cardEntityList.stream().
                map(m->new CardListDto(
                        m.getCardId(),
                        m.getUser().getId(),
                        m.getBank().getId(),
                        m.getCardName(),
                        m.getSettlementDay(),
                        m.getSettlementDate(),
                        m.getPaymentAmt()
                ))
                .collect(Collectors.toList());
    }



    public CardDto selectOneByCardId(long cardId){
        CardEntity cardEntity = cardRepository.findByCardId(cardId);
        CardDto cardDto = CardDto.builder()
                .cardId(cardEntity.getCardId())
                .userId(cardEntity.getUser().getId())
                .bankId(cardEntity.getBank().getId())
                .cardName(cardEntity.getCardName())
                .settlementDay(cardEntity.getSettlementDay())
                .settlementDate(cardEntity.getSettlementDate())
                .paymentAmt(cardEntity.getPaymentAmt())
                .build();
        return cardDto;
    }

    @Transactional
    public CardDto changeNickname(long cardId, String nickname){
        CardEntity cardEntity = cardRepository.getById(cardId);
        cardEntity.changeNickname(nickname);
        CardDto cardDto = CardDto.builder()
                .cardId(cardEntity.getCardId())
                .userId(cardEntity.getUser().getId())
                .bankId(cardEntity.getBank().getId())
                .cardName(cardEntity.getCardName())
                .settlementDay(cardEntity.getSettlementDay())
                .settlementDate(cardEntity.getSettlementDate())
                .paymentAmt(cardEntity.getPaymentAmt())
                .build();
        return cardDto;
    }

    public List<CardListDto> cardListByUserId(Long userId) {
        List<CardEntity> cardEntityList = cardRepository.findByUserId(userId);
        return cardEntityList.stream().
                map(m->new CardListDto(
                        m.getCardId(),
                        m.getUser().getId(),
                        m.getBank().getId(),
                        m.getCardName(),
                        m.getSettlementDay(),
                        m.getSettlementDate(),
                        m.getPaymentAmt()
                ))
                .collect(Collectors.toList());
    }
}

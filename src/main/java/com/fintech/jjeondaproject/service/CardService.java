package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.card.CardDetailDto;
import com.fintech.jjeondaproject.dto.card.CardDto;
import com.fintech.jjeondaproject.dto.card.CardListDto;
import com.fintech.jjeondaproject.entity.card.CardEntity;
import com.fintech.jjeondaproject.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
                        m.getBank().getBankName(),
                        m.getCardName(),
                        m.getSettlementDay(),
                        m.getSettlementDate(),
                        m.getPaymentAmt()
                ))
                .collect(Collectors.toList());
    }

    public List<CardListDto> cardListByUserIdAndBankId(Long userId, Long bankId){

        List<CardEntity> cardEntityList = cardRepository.findByUserIdAndBankId(userId, bankId);
        return cardEntityList.stream().
                map(m->new CardListDto(
                        m.getCardId(),
                        m.getUser().getId(),
                        m.getBank().getId(),
                        m.getBank().getBankName(),
                        m.getCardName(),
                        m.getSettlementDay(),
                        m.getSettlementDate(),
                        m.getPaymentAmt()
                ))
                .collect(Collectors.toList());
    }



    public CardDto selectOneByCardId(Long cardId){
        CardEntity cardEntity = cardRepository.findByCardId(cardId);


        CardDto cardDto = CardDto.builder()
                .cardId(cardEntity.getCardId())
                .userId(cardEntity.getUser().getId())
                .bankId(cardEntity.getBank().getId())
                .bankName(cardEntity.getBank().getBankName())
                .cardName(cardEntity.getCardName())
                .settlementDay(cardEntity.getSettlementDay())
                .settlementDate(cardEntity.getSettlementDate())
                .paymentAmt(cardEntity.getPaymentAmt())
                .build();
        return cardDto;
    }

    public CardDetailDto cardDetailByCardId(Long cardId){
        CardEntity cardEntity = cardRepository.findByCardId(cardId);

        LocalDateTime settlementDate = cardEntity.getSettlementDate(); // 이 부분은 실제로는 cardDetail.getSettlementDate()로부터 받은 값으로 대체해야 합니다.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String formattedDate = settlementDate.format(formatter);

        String formattedPaymentAmt = String.format("%,d 원", cardEntity.getPaymentAmt());


        CardDetailDto cardDetailDto = CardDetailDto.builder()
                .cardId(cardEntity.getCardId())
                .userId(cardEntity.getUser().getId())
                .bankId(cardEntity.getBank().getId())
                .bankName(cardEntity.getBank().getBankName())
                .cardName(cardEntity.getCardName())
                .settlementDay(cardEntity.getSettlementDay())
                .settlementDate(formattedDate)
                .paymentAmt(formattedPaymentAmt)
                .build();
        return cardDetailDto;
    }

    @Transactional
    public CardDto changeNickname(Long cardId, String nickname){
        CardEntity cardEntity = cardRepository.getById(cardId);
        cardEntity.changeNickname(nickname);
        CardDto cardDto = CardDto.builder()
                .cardId(cardEntity.getCardId())
                .userId(cardEntity.getUser().getId())
                .bankId(cardEntity.getBank().getId())
                .bankName(cardEntity.getBank().getBankName())
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
                        m.getBank().getBankName(),
                        m.getCardName(),
                        m.getSettlementDay(),
                        m.getSettlementDate(),
                        m.getPaymentAmt()
                ))
                .collect(Collectors.toList());
    }
    public List<CardEntity> bankListByUserId(Long userId) {

        List<Long> bankIds = cardRepository.findDistinctBankIdByUserId(userId);
        List<CardEntity> cards = new ArrayList<>();
        for (Long bankId : bankIds) {
            Optional<CardEntity> cardEntityOptional = cardRepository.findFirstByBankId(bankId);
            if(cardEntityOptional.isPresent()) {
                cards.add(cardEntityOptional.get());
            }
        }
        return cards;
    }

//    public List<CardListDto> cardListByBankId(Long bankId) {
//        List<CardEntity> cardEntityList = cardRepository.findByBankId(bankId);
//        return cardEntityList.stream().
//                map(m->new CardListDto(
//                        m.getCardId(),
//                        m.getUser().getId(),
//                        m.getBank().getId(),
//                        m.getBank().getBankName(),
//                        m.getCardName(),
//                        m.getSettlementDay(),
//                        m.getSettlementDate(),
//                        m.getPaymentAmt()
//                ))
//                .collect(Collectors.toList());
//    }
}

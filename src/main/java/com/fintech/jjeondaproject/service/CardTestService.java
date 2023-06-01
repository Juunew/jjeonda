package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.card.CardListDto;
import com.fintech.jjeondaproject.entity.CardEntity;
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
                map(m->new CardListDto(m.getId(),
                        m.getUser(),
                        m.getBank(),
                        m.getCardName(),
                        m.getSettlementDay(),
                        m.getSettlementDate(),
                        m.getPaymentAmt()
                        ))
                .collect(Collectors.toList());


    }
}

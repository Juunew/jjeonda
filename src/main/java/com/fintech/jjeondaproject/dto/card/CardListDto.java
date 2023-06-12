package com.fintech.jjeondaproject.dto.card;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardListDto {

    private Long cardId;
    private Long userId;
    private Long bankId;
    private String cardName;
    private String settlementDay;
    private LocalDateTime settlementDate;
    private int paymentAmt;
}

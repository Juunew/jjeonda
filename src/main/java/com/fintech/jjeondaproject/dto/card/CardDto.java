package com.fintech.jjeondaproject.dto.card;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private Long cardId;
    private Long userId;
    private Long bankId;
    private String cardName;
    private String settlementDay;
    private LocalDateTime settlementDate;
    private int paymentAmt;

}

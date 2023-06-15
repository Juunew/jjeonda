package com.fintech.jjeondaproject.dto.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailDto {
    private Long cardId;
    private Long userId;
    private Long bankId;
    private String bankName;
    private String cardName;
    private String settlementDay;
    private String settlementDate;
    private String paymentAmt;

}

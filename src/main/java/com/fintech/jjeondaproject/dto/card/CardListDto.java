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

//    public CardListDto(Long id, String cardName, BankEntity bank, UserEntity user, int paymentAmt, LocalDateTime settlementDate, String settlementDay) {
//        this.cardId = id;
//        this.cardName = cardName;
//        this.bank = bank;
//        this.user = user;
//        this.paymentAmt = paymentAmt;
//        this.settlementDate = settlementDate;
//        this.settleMentDay = settlementDay;
//    }
}

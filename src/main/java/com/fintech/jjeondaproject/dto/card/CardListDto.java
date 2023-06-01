package com.fintech.jjeondaproject.dto.card;

import com.fintech.jjeondaproject.entity.BankEntity;
import com.fintech.jjeondaproject.entity.UserEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardListDto {

    private long cardId;
    private UserEntity user;
    private BankEntity bank;
    private String cardName;
    private String settleMentDay;
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

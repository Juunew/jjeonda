package com.fintech.jjeondaproject.dto.card;

import com.fintech.jjeondaproject.entity.BankEntity;
import com.fintech.jjeondaproject.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private long cardId;
    private UserEntity user;
    private BankEntity bank;
    private String cardName;
    private String settlementDay;
    private LocalDateTime settlementDate;
    private int paymentAmt;

}

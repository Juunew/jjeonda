package com.fintech.jjeondaproject.entity.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fintech.jjeondaproject.entity.bank.BankEntity;
import com.fintech.jjeondaproject.entity.user.UserEntity;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "card")
@Getter
@Entity
public class CardEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private BankEntity bank;

    @Column(name = "card_name", nullable = false)
    private String cardName;

    @Column(name = "settlement_day", nullable = false)
    private String settlementDay;

    @Column(name = "settlement_date")
    private LocalDateTime settlementDate;

    @Column(name = "payment_amt")
    private int paymentAmt;


    public void changeNickname(String cardName){
        this.cardName = cardName;
    }
}

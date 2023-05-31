package com.fintech.jjeondaproject.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "card")
@Getter
@Entity
public class CardEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "card_name", nullable = false)
    private String cardName;

    @Column(name = "settlement_day", nullable = false)
    private String settlementDay;

    @Column(name = "settlement_date")
    private LocalDateTime settlementDate;

    @Column(name = "payment_amt")
    private int paymentAmt;

}

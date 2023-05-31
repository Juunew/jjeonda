package com.fintech.jjeondaproject.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "bank")
@Getter
@Entity
public class BankEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long id;

    @Column(name = "bank_code", length = 3)
    private String bankCode;

    @Column(name = "card_code", length = 3)
    private String cardCode;

    @Column(name = "bank_name")
    private String bankName;
}

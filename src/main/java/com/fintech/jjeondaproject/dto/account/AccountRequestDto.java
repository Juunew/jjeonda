package com.fintech.jjeondaproject.dto.account;

import com.fintech.jjeondaproject.entity.bank.BankEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountRequestDto {
	
	private BankEntity bankId;		//기관식별자
	private BankEntity bankCode;	//은행코드	
	private BankEntity bankName;	//은행명
	private Long availableAmt;		//잔액
	
}

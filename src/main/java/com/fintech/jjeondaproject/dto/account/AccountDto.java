package com.fintech.jjeondaproject.dto.account;

import java.sql.Time;
import java.util.Date;

import com.fintech.jjeondaproject.entity.BankEntity;
import com.fintech.jjeondaproject.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AccountDto {
	
	private Long accountId;			//pk
	
	private UserEntity user;				
	private BankEntity bank;				
	private Long accountNum;		//계좌번호
	private Long availableAmt;		//잔액(출금가능)
	private Date tranDate;			//거래일
	private Time tranTime;			//거래시각
	private String inoutType;		//입출금구분
	private String content;			//사용처
	private int tranAmt;			//거래금액
	private int tranAfterAmt;		//거래후잔액
	
}

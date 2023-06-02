package com.fintech.jjeondaproject.dto.account;

import java.sql.Time;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountRequestDto {
	
	private Long accountId;			//pk
	
	private Long user;				
	private Long bank;				
	private Long accountNum;		//계좌번호1	
	private Long availableAmt;		//잔액(출금가능)
	private Date tranDate;			//거래일
	private Time tranTime;			//거래시각
	private String inoutType;		//입출금구분
	private String content;			//사용처
	private int tranAmt;			//거래금액
	private int tranAfterAmt;		//거래후잔액
	
	/*
	 * @Builder public AccountRequestDto() {
	 * 
	 * }
	 */
	
}

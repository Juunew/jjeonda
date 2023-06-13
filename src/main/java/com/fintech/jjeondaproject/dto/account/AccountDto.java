package com.fintech.jjeondaproject.dto.account;

import java.sql.Time;
import java.util.Date;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountDto {
	
	private Long accountId;			//pk
	
	private Long userId;
	private String bankCode;
	private String bankName;
	private String accountNum;		//계좌번호
	private Long availableAmt;		//잔액(출금가능)
	private Date tranDate;			//거래일
	private Time tranTime;			//거래시각
	private String inoutType;		//입출금구분
	private String content;			//사용처
	private int tranAmt;			//거래금액
	private int tranAfterAmt;		//거래후잔액

}

package com.fintech.jjeondaproject.dto.account;

import java.sql.Time;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {

	private Date tranDate;
	private Time tranTime;
	private String inoutType;
	private String content;
	private int tranAmt;
	private int tranAfterAmt;
	
}

package com.fintech.jjeondaproject.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BankDto {
	
	private Long id;
	private String bankCode;
	private String bankName;
	
}

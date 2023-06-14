package com.fintech.jjeondaproject.dto.account;

import lombok.Data;

@Data
public class AccountReqDto {

    private Long bankId;
    private String accountNum;
    private Long availableAmt;
}
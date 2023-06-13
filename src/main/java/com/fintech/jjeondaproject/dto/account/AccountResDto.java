package com.fintech.jjeondaproject.dto.account;

import com.fintech.jjeondaproject.entity.account.AccountEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Time;
import java.util.Date;

import com.fintech.jjeondaproject.entity.account.AccountEntity;

@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AccountResDto {

    private Long accountId;
    private String bankName;
    private String accountNum;
    private Date tranDate;
    private Time tranTime;
    private String inoutType;
    private String content;
    private int tranAmt;
    private int tranAfterAmt;

    public AccountResDto(AccountEntity accountEntity) {
        this.accountId = accountEntity.getId();
        this.bankName = accountEntity.getBank().getBankName();
        this.accountNum = accountEntity.getAccountNum();
        this.tranDate = accountEntity.getTranDate();
        this.tranTime = accountEntity.getTranTime();
        this.inoutType = accountEntity.getInoutType();
        this.content = accountEntity.getContent();
        this.tranAmt = accountEntity.getTranAmt();
        this.tranAfterAmt = accountEntity.getTranAfterAmt();
    }

    public static AccountResDto fromEntity(AccountEntity accountEntity) {
        return new AccountResDto(
                accountEntity.getId(),
                accountEntity.getBank().getBankName(),
                accountEntity.getAccountNum(),
                accountEntity.getTranDate(),
                accountEntity.getTranTime(),
                accountEntity.getInoutType(),
                accountEntity.getContent(),
                accountEntity.getTranAmt(),
                accountEntity.getTranAfterAmt()
        );
    }
}

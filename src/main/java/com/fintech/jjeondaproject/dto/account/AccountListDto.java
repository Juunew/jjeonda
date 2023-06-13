package com.fintech.jjeondaproject.dto.account;

import com.fintech.jjeondaproject.entity.account.AccountEntity;
import com.fintech.jjeondaproject.entity.account.AccountEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AccountListDto {

    private Long accountId;
    private String accountNum;
    private String bankName;
    private Long availableAmt;

    // 설명의 이해를 돕기위한 생성자
    public AccountListDto(AccountEntity accountEntity) {
        this.accountId = accountEntity.getId();
        this.accountNum = accountEntity.getAccountNum();
        this.bankName = accountEntity.getBank().getBankName();
        this.availableAmt = accountEntity.getAvailableAmt();
    }

    public static AccountListDto fromEntity(AccountEntity accountEntity) {
        return new AccountListDto(
                accountEntity.getId(),
                accountEntity.getAccountNum(),
                accountEntity.getBank().getBankName(),
                accountEntity.getAvailableAmt()
        );
    }
}

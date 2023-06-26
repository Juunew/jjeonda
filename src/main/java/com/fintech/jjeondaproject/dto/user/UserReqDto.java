package com.fintech.jjeondaproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserReqDto {

    private String accountId;
    private String password;
    private String name;
    private String phone;
    private String gender;
    private String birth;
    private String email;
    private String agreementYn;

}

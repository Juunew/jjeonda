package com.fintech.jjeondaproject.dto.user.login;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginReqDto {

    private String accountId;
    private String password;
}

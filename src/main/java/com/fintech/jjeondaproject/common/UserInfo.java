package com.fintech.jjeondaproject.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserInfo {

    private Long userId;
    private String accountId;
    private String userName;

    public static UserInfo of(Long userId, String accountId, String userName) {
        return new UserInfo(
                userId,
                accountId,
                userName
        );
    }
}

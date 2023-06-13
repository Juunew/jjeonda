package com.fintech.jjeondaproject.common;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserInfo {

    private Long userId;
    private String userName;
    private String userEmail;

    public static UserInfo of(Long userId, String userName, String userEmail) {
        return new UserInfo(
                userId,
                userName,
                userEmail
        );
    }
}

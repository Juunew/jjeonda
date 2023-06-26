package com.fintech.jjeondaproject.util.jwt;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class JwtTokenDto {

    private String accessToken;
    private String refreshToken;

    public static JwtTokenDto of(String accessToken, String refreshToken) {
        return new JwtTokenDto(
                accessToken,
                refreshToken
        );
    }
}

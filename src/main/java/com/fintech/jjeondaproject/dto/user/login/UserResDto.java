package com.fintech.jjeondaproject.dto.user.login;

import com.fintech.jjeondaproject.entity.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResDto {

    private Long userId;
    private String userName;
    private String accessToken;
    private Long refreshTokenId;

    public static UserResDto of(Long userId, String userName, String accessToken, Long refreshTokenId) {
        return new UserResDto(
                userId,
                userName,
                accessToken,
                refreshTokenId
        );
    }

}

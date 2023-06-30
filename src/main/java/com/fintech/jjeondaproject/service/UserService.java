package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.common.UserInfo;
import com.fintech.jjeondaproject.common.constant.errorType.UserError;
import com.fintech.jjeondaproject.dto.user.UserReqDto;
import com.fintech.jjeondaproject.dto.user.login.LoginReqDto;
import com.fintech.jjeondaproject.dto.user.login.UserResDto;
import com.fintech.jjeondaproject.entity.TokenEntity;
import com.fintech.jjeondaproject.entity.user.UserEntity;
import com.fintech.jjeondaproject.exception.UserException;
import com.fintech.jjeondaproject.repository.TokenRepository;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.util.jwt.JwtTokenDto;
import com.fintech.jjeondaproject.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtUtil jwtUtil;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private long expiredTimeMs;

    @Value("${jwt.token.refresh-expired-time-ms}")
    private long reExpiredTimeMs;

    public Long createUser(UserReqDto reqDto) {
        UserEntity userEntity = UserEntity.of(reqDto);

        return userRepository.save(userEntity).getId();
    }

    public UserResDto loginUser(LoginReqDto reqDto) {
        UserEntity user = getUserEntityOrException(reqDto.getAccountId());

        // TODO: jwt token 발급 flow 다시 살펴보기, 꼭 UserInfo 를 input 해야하나?
        UserInfo userInfo = UserInfo.of(
                user.getId(),
                user.getAccountId(),
                user.getName());

        JwtTokenDto jwtTokenDto = jwtUtil.generateAccessToken(userInfo, secretKey, expiredTimeMs, reExpiredTimeMs);

        Long refreshTokenId = savedRefreshToken(user.getId(), jwtTokenDto.getRefreshToken());

        return UserResDto.of(
                user.getId(),
                user.getName(),
                jwtTokenDto.getAccessToken(),
                refreshTokenId
        );
    }

    private Long savedRefreshToken(Long userId, String refreshToken) {
        TokenEntity tokenEntity = TokenEntity.of(
                userId,
                refreshToken
        );

       return tokenRepository.save(tokenEntity).getId();
    }

    private UserEntity getUserEntityOrException(String accountId) {
        UserEntity user = userRepository.findByAccountId(accountId);

        if (user == null) {
            throw new UserException(UserError.USER_NOT_FOUND);
        }

        return user;
    }
}

package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.common.UserInfo;
import com.fintech.jjeondaproject.dto.user.UserReqDto;
import com.fintech.jjeondaproject.dto.user.login.LoginReqDto;
import com.fintech.jjeondaproject.dto.user.login.UserResDto;
import com.fintech.jjeondaproject.entity.TokenEntity;
import com.fintech.jjeondaproject.entity.user.UserEntity;
import com.fintech.jjeondaproject.repository.TokenRepository;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.util.jwt.JwtTokenDto;
import com.fintech.jjeondaproject.util.jwt.JwtUtil;
import org.apache.catalina.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TokenRepository tokenRepository;

    @Test
    @DisplayName("회원가입")
    void user_sign_up() {
        // given
        UserEntity user = UserEntity.of(setReqDto());

        // when
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
        userService.createUser(setReqDto());

        // then
        verify(userRepository).save(user);
    }

    @Test
    void user_sign_in() {
        // given
        LoginReqDto reqDto = createLoginReq("test1", "1234");
        UserEntity user = createEntity();
        JwtTokenDto jwtTokenDto = new JwtTokenDto("accessToken", "refreshToken");
        TokenEntity tokenEntity = TokenEntity.of(user.getId(), jwtTokenDto.getRefreshToken());

        when(userRepository.findByAccountId(reqDto.getAccountId())).thenReturn(user);
        when(jwtUtil.generateAccessToken(any(), any(), anyLong(), anyLong())).thenReturn(jwtTokenDto);
        when(tokenRepository.save(any(TokenEntity.class))).thenReturn(tokenEntity);

        UserResDto resDto = userService.loginUser(reqDto);

        // then
        assertEquals(resDto.getUserId(), user.getId());
        assertEquals(resDto.getUserName(), user.getName());
        assertEquals(resDto.getAccessToken(), jwtTokenDto.getAccessToken());

        verify(userRepository).findByAccountId(reqDto.getAccountId());
        verify(jwtUtil).generateAccessToken(any(), any(), anyLong(), anyLong());
        verify(tokenRepository).save(any(TokenEntity.class));
    }

    private UserReqDto setReqDto() {
        return new UserReqDto(
                "test1",
                "test1",
                "테스트",
                "01011112222",
                "M",
                "19921203",
                "test@jjeonda.com",
                "Y"
        );
    }

    private LoginReqDto createLoginReq(String accountId, String password) {
        return new LoginReqDto(
                accountId,
                password
        );
    }

    private UserEntity createEntity() {
        return new UserEntity(
                1L,
                "test1",
                "1234",
                "test",
                "01011112222",
                "M",
                "19921203",
                "test@jjeonda.com",
                "Y"
        );
    }
}
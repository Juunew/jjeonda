package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.user.UserReqDto;
import com.fintech.jjeondaproject.entity.user.UserEntity;
import com.fintech.jjeondaproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    /*@Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${token.expired-time-ms}")
    private long expiredTimeMs;

    @Value("${token.refresh-expired-time-ms}")
    private long reExpiredTimeMs;*/

    public Long createUser(UserReqDto reqDto) {
        UserEntity userEntity = UserEntity.of(reqDto);

        return userRepository.save(userEntity).getId();
    }
}

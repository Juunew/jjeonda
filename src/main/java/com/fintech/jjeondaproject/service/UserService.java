package com.fintech.jjeondaproject.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.jjeondaproject.common.constant.errorType.UserError;
import com.fintech.jjeondaproject.dto.user.ProfileResponseDto;

import com.fintech.jjeondaproject.dto.user.UserDto;
import com.fintech.jjeondaproject.dto.user.UserLoginDto;
import com.fintech.jjeondaproject.entity.user.UserEntity;
import com.fintech.jjeondaproject.exception.UserException;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.util.Encryption;
import com.fintech.jjeondaproject.util.jwt.Jwt;
import com.fintech.jjeondaproject.util.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;
	
	public void join(UserDto userDto){
		System.out.println("userDto.getPassword():"+userDto.getPassword());
		String myEncryption = Encryption.encryptSHA512(userDto.getPassword());
		System.out.println("myEn:"+ myEncryption);
		UserEntity userEntity = UserEntity.builder()
				.accountId(userDto.getAccountId())
				.password(myEncryption)
				.name(userDto.getName())
				.phone(userDto.getPhone())
				.gender(userDto.getGender())
				.birth(userDto.getBirth())
				.email(userDto.getEmail())
				.regDate(userDto.getRegDate())
				.agreementYn(userDto.getAgreementYn())
				.build();
		userRepository.save(userEntity);
		
	}
	
	// id중복확인
	public boolean checkAccountId(String accountId) {
		return userRepository.existsByAccountId(accountId);
	}

	public String signIn(UserLoginDto userDto) {
//		log.info("userDto={}",userDto.getPassword());
		UserEntity savedUser =  userRepository.findByAccountId(userDto.getAccountId());
		
		if(savedUser == null) {
			throw new UserException(UserError.USER_NOT_FOUND);
		}
//		log.info("Encryption.encryptSHA512(userDto.getPassword()):{}",encryption.encryptSHA512(userDto.getPassword()));
		if(Encryption.comparePwd(userDto.getPassword(), savedUser.getPassword())) { // input pwd와 db pwd가 같다면..
			Jwt jwt = jwtProvider.putClaim(savedUser);
			savedUser.updateRefreshToken(jwt.getRefreshToken());
			userRepository.save(savedUser); // refreshToken db에 저장

			return jwt.getAccessToken();
		}
		return "로그인 실패";
	}

	public void logout(HttpServletResponse response, String cookieName) {
		Cookie cookie = new Cookie(cookieName, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);

	}

}

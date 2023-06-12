package com.fintech.jjeondaproject.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fintech.jjeondaproject.dto.user.NaverResponseDto;
import com.fintech.jjeondaproject.dto.user.ProfileResponseDto;
import com.fintech.jjeondaproject.entity.UserEntity;
import com.fintech.jjeondaproject.loginFeign.LoginFeign;
import com.fintech.jjeondaproject.loginFeign.ProfileFeign;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.util.jwt.Jwt;
import com.fintech.jjeondaproject.util.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class NaverService {
	private final LoginFeign loginFeign;
	private final ProfileFeign profileFeign;
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;
	
	@Value("${oauth.naver.client_id}")
    private String clientId;
    @Value("${oauth.naver.client_secret}")
    private String clientSecret;
    @Value("${oauth.naver.callback}")
    private String callback;
    @Value("${oauth.naver.state}")
    private String state;
    
    public String getRequireUrl() { // 인가코드받기
    	return "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id="+clientId+"&redirect_uri="+callback+"&state="+state;
    }

	public String getProfile(String code, String state2) {
		NaverResponseDto resDto = loginFeign.login("authorization_code", clientId, clientSecret, code, state);
		String accessToken = resDto.getToken_type() + " " +resDto.getAccess_token();
		ProfileResponseDto join = profileFeign.getProfile(accessToken);
		String birth = join.getResponse().getBirthyear()+join.getResponse().getBirthday();
		UserEntity user = new UserEntity(join.getResponse().getName(),
										join.getResponse().getMobile(),
										join.getResponse().getGender(),
										birth,
										join.getResponse().getEmail());
		userRepository.save(user);
//		System.out.println("userRepository.save(user):"+userRepository.save(user));
		Jwt jwt = jwtProvider.putClaim(user);
//		System.out.println("jwt네이버서비스:"+jwt.getAccessToken());
		user.updateRefreshToken(jwt.getRefreshToken());
		return jwt.getAccessToken();
		
	}
    
	
}

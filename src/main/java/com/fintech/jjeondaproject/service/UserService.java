package com.fintech.jjeondaproject.service;

import org.springframework.stereotype.Service;

import com.fintech.jjeondaproject.dto.user.UserDto;
import com.fintech.jjeondaproject.entity.UserEntity;
import com.fintech.jjeondaproject.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;

	public void join(UserDto userDto) {
		UserEntity userEntity = UserEntity.builder()
				.id(userDto.getId())
				.password(userDto.getPassword())
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

	
	
}

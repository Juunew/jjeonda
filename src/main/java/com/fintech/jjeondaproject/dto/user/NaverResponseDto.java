package com.fintech.jjeondaproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NaverResponseDto {
	String access_token;
	String refresh_token;
	String token_type;
	Integer expires_in;
}

package com.fintech.jjeondaproject.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
	String access_token;
	String refresh_token;
	String token_type;
	String expires_in;
	String refresh_token_expires_in;
}

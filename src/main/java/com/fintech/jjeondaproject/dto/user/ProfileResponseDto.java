package com.fintech.jjeondaproject.dto.user;

import feign.Response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileResponseDto {
	public String resultcode;
	public String message;
	public Response response;
	
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Response{
		String name;
		String email;
		String gender;
		String birthday;
		String birthyear;
		String mobile;
	}
	
}

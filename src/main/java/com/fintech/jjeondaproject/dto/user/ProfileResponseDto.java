package com.fintech.jjeondaproject.dto.user;

import feign.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponseDto {
	public String resultcode;
	public String message;
	public Response response;
	
	
	public static class Response{
		public String kakaoId;
		public String email;
		public String gender;
		public String birth;
		public String regDate;
	}
}

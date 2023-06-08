package com.fintech.jjeondaproject.dto.user;

import groovy.transform.builder.Builder;
import lombok.Data;
@Builder
@Data
public class UserLoginDto {
	private Long userId;
	private String id;
	private String password;
}

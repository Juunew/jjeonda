package com.fintech.jjeondaproject.dto.user;

import groovy.transform.builder.Builder;
import lombok.Data;
@Builder
@Data
public class UserLoginDto {
	private String accountId;
	private String password;
}

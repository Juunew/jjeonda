package com.fintech.jjeondaproject.dto.user;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserTestDto {
	private Long userId;
	private String id;
	private String password;
	private String name;
	private String phone;
	private String gender;
	private String birth;
	private String email;
	private Date regDate;
	private String aggrementYn;
}

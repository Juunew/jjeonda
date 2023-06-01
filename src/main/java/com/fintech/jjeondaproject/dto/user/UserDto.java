package com.fintech.jjeondaproject.dto.user;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fintech.jjeondaproject.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
	private Long userId;
	
	@NotEmpty(message = "사용자ID는 필수항목입니다.")
	private String id;
	private String password;
	private String name;
	private String phone;
	private String gender;
	private String birth;
	
	@Email
	private String email;
	private Date regDate;
	private String agreementYn;
	
//	// dto -> entity
//	UserEntity toEntity() {
//		return new UserEntity(this.userId, this.id, this.password, this.name, this.phone, 
//				this.gender, this.birth, this.email, this.regDate, this.aggrementYn);
//	}
}

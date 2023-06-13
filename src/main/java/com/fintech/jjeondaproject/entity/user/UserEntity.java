package com.fintech.jjeondaproject.entity.user;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fintech.jjeondaproject.entity.BaseTime;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@Getter
@Entity
public class UserEntity extends BaseTime {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // identity, sequence, table, auto
	@Column(name = "user_id")
	private Long id;
	
//	@NotBlank(message = "사용자ID는 필수항목입니다.")
	@Column(nullable = false, length = 200, name = "account_id")
	private String accountId;
	
	private String password;
	
	private String name;
	
	private String phone;
	
	private String gender;
	
	private String birth;
	
	private String email;
	
	@Column(name = "reg_date")
	@CreationTimestamp // update는 UpdateTimestamp
	private Date regDate;
	
	@Column(name = "agreement_yn")
	private String agreementYn;
	
	@Column(name = "refresh_token")
	private String refreshToken;
	
	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public UserEntity(String name, String phone, String gender, String birth, String email) {
		this.name = name;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
		this.email = email;
	}
	
	

}

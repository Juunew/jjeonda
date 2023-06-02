package com.fintech.jjeondaproject.entity;

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
public class UserEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // identity, sequence, table, auto
	@Column
	private Long userId;
	
//	@NotBlank(message = "사용자ID는 필수항목입니다.")
	@Column(nullable = false, length = 200)
	private String id;
	
	@Column(nullable = false, length = 200)
	private String password;
	
	@Column(nullable = false, length = 200)
	private String name;
	
	@Column(nullable = false, length = 200)
	private String phone;
	
	@Column(nullable = false, length = 200)
	private String gender;
	
	@Column(nullable = false, length = 200)
	private String birth;
	
	@Column(nullable = false, length = 200)
	private String email;
	
	@Column(name = "reg_date", nullable = false, length = 200)
	@CreationTimestamp // update는 UpdateTimestamp
	private Date regDate;
	
	@Column(name = "agreement_yn", nullable = false, length = 1)
	private String agreementYn;

}

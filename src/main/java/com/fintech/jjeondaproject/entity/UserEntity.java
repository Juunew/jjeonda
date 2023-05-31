package com.fintech.jjeondaproject.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // identity, sequence, table, auto
	private Long userId;
	
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
	
	@Column(nullable = false, length = 200)
	private Date regDate;
	
	@Column(nullable = false, length = 1)
	private String agreementYn;
}

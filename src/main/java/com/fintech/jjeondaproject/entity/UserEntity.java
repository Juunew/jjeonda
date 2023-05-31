package com.fintech.jjeondaproject.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
@Getter
@Entity
public class UserEntity {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) // identity, sequence, table, auto
	@Column
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
	
	@Column(name = "reg_date", nullable = false, length = 200)
	private Date regDate;
	
	@Column(name = "aggrement_yn", nullable = false, length = 1)
	private String agreementYn;
	
	@OneToMany(mappedBy = "userEntity")
	List<BookEntity> bookEntity = new ArrayList<BookEntity>();
	
	@OneToMany(mappedBy = "userEntity")
	List<BankEntity> bankEntity = new ArrayList<BankEntity>();
	
	@OneToMany(mappedBy = "userEntity")
	List<CardEntity> cardEntity = new ArrayList<CardEntity>();
	
}

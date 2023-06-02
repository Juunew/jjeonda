package com.fintech.jjeondaproject.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="account")
@Getter
@Entity
public class AccountEntity {
	
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long accountId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "bank_id")
	private BankEntity bank;
	
	@Column(nullable=false)
	private Long accountNum;
	
	@Column(nullable=false)   
	private Long availableAmt;
	
	@Column(nullable=false)
	private Date tranDate;
	
	@Column(nullable=false)
	private Time tranTime;
	
	@Column(nullable=false, length=2)
	private String inoutType;
	
	@Column(nullable=false)
	private String content;
	
	@Column(nullable=false)
	private int tranAmt;
	
	@Column(nullable=false)
	private int tranAfterAmt;

	
}

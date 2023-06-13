package com.fintech.jjeondaproject.entity.account;

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

import com.fintech.jjeondaproject.entity.bank.BankEntity;
import com.fintech.jjeondaproject.entity.user.UserEntity;

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
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;
	
	@ManyToOne
	@JoinColumn(name = "bank_id")
	private BankEntity bank;
	
	@Column(nullable=false)
	private String accountNum;		//계좌번호
	
	@Column(nullable=false)   
	private Long availableAmt;		//잔액(출금가능)
	
	@Column(nullable=false)
	private Date tranDate;			//거래일
	
	@Column(nullable=false)
	private Time tranTime;			//거래시각
	
	@Column(nullable=false)
	private String inoutType;		//입출금구분
	
	@Column(nullable=false)
	private String content;			//사용처
	
	@Column(nullable=false)
	private int tranAmt;			//거래금액
	
	@Column(nullable=false)
	private int tranAfterAmt;		//거래후잔액

	
}

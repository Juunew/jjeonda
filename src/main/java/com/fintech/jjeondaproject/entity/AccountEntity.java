package com.fintech.jjeondaproject.entity;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {
	
	@Column(nullable=false)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long accountId;
	
	@Column(nullable=false)
	private Long userId;
	
	@Column(nullable=false)
	private Long bankId;
	
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

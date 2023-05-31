package com.fintech.jjeondaproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "book_id")
	private Long bookId;
	
	private Long userId;
	
	@Column(nullable = false)
	private int budget;
	private int income;
	private int fixedCost;
	private int variableCost;
	private int balance;
	private int cost;
	
	@Column(length = 4, nullable = false)
	private String year;
	
	@Column(length = 2, nullable = false)
	private String month;
	
	@Column(length = 4000, nullable = false)
	private String content;
	
	private String memo;
}

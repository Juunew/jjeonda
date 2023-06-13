package com.fintech.jjeondaproject.entity.book;

import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyModDto;
import com.fintech.jjeondaproject.dto.book.monthly.BookMonthlyReqDto;
import com.fintech.jjeondaproject.entity.BaseTime;
import com.fintech.jjeondaproject.entity.user.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "total_book")
@Getter
@Entity
public class TotalBookEntity extends BaseTime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

	@Column(length = 4, nullable = false)
	private String year;
	
	@Column(length = 2, nullable = false)
	private String month;

	@Column(nullable = false)
	private int budget;

	@Column(name = "remain_budget")
	private int remainBudget;

	@OneToMany(mappedBy = "totalBook")
	private List<DetailBookEntity> detailBook = new ArrayList<>();

	public TotalBookEntity(String year, String month, int budget, int remainBudget) {
		this.year = year;
		this.month = month;
		this.budget = budget;
		this.remainBudget = remainBudget;
	}

	public static TotalBookEntity of(BookMonthlyReqDto reqDto) {
		return new TotalBookEntity(
				reqDto.getYear(),
				reqDto.getMonth(),
				reqDto.getBudget(),
				reqDto.getBudget()
		);
	}

	public void updateBudget(BookMonthlyModDto modDto) {
		this.budget = modDto.getBudget();
	}

	public void updateRemainBudget(int remainBudget) {
		this.remainBudget = remainBudget;
	}
}

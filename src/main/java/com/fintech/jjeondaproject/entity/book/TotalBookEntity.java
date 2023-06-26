package com.fintech.jjeondaproject.entity.book;

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

}

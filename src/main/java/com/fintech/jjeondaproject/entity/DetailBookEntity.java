package com.fintech.jjeondaproject.entity;

import com.fintech.jjeondaproject.dto.book.detail.BookDetailModDto;
import com.fintech.jjeondaproject.dto.book.detail.BookDetailReqDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "detail_book")
@Getter
@Entity
public class DetailBookEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private TotalBookEntity totalBook;

    // 수입-> in
    // 고정지출 -> fc
    // 변동지출 -> vc
    @Column(name = "cost_type", length = 2)
    private String costType;

    private int cost;

    @Column(length = 4000, nullable = false)
    private String content;
    private String memo;

    public DetailBookEntity(TotalBookEntity totalBook, String costType, int cost, String content, String memo) {
        this.totalBook = totalBook;
        this.costType = costType;
        this.cost = cost;
        this.content = content;
        this.memo = memo;
    }

    public static DetailBookEntity of(BookDetailReqDto detailReqDto, TotalBookEntity totalBook) {
        return new DetailBookEntity(
                totalBook,
                detailReqDto.getCostType(),
                detailReqDto.getCost(),
                detailReqDto.getContent(),
                detailReqDto.getMemo()
        );
    }

    public void updateExpenditureDetail(BookDetailModDto modDto) {
        this.costType = modDto.getCostType();
        this.cost = modDto.getCost();
        this.content = modDto.getContent();
        this.memo = modDto.getMemo();
    }
}

package com.fintech.jjeondaproject.dto.book.detail;

import com.fintech.jjeondaproject.entity.DetailBookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookDetailResDto {

    private Long detailBookId;
    private String costType;
    private int cost;
    private String content;
    private String memo;

    public static BookDetailResDto fromEntity(DetailBookEntity detailBook) {
        return new BookDetailResDto(
                detailBook.getId(),
                detailBook.getCostType(),
                detailBook.getCost(),
                detailBook.getContent(),
                detailBook.getMemo()
        );
    }
}

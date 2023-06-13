package com.fintech.jjeondaproject.dto.book.detail;

import com.fintech.jjeondaproject.entity.DetailBookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookDetailsDto {

    private Long detailBookId;
    private String costType;
    private int cost;
    private String content;
    private String memo;

    public static BookDetailsDto fromEntity(DetailBookEntity detailBookEntity) {
        return new BookDetailsDto(
                detailBookEntity.getId(),
                detailBookEntity.getCostType(),
                detailBookEntity.getCost(),
                detailBookEntity.getContent(),
                detailBookEntity.getMemo()
        );
    }
}

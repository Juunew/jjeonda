package com.fintech.jjeondaproject.dto.book;

import com.fintech.jjeondaproject.dto.book.detail.BookDetailsDto;
import com.fintech.jjeondaproject.entity.DetailBookEntity;
import com.fintech.jjeondaproject.entity.TotalBookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class BookListDto {

    private Long bookId;
    private String year;
    private String month;
    private int budget;
    private int remainBudget;
    private List<BookDetailsDto> details;

    public static BookListDto fromEntities(TotalBookEntity totalBook, List<DetailBookEntity> detailBook) {
        return new BookListDto(
                totalBook.getId(),
                totalBook.getYear(),
                totalBook.getMonth(),
                totalBook.getBudget(),
                totalBook.getRemainBudget(),
                detailBook.stream()
                        .map(BookDetailsDto::fromEntity)
                        .collect(Collectors.toList())
        );
    }
}

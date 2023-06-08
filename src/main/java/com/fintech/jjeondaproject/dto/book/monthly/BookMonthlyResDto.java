package com.fintech.jjeondaproject.dto.book.monthly;

import com.fintech.jjeondaproject.entity.TotalBookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookMonthlyResDto {

    private Long bookId;
    private String year;
    private String month;
    private int budget;
    private int remainBudget;

    public static BookMonthlyResDto fromEntity(TotalBookEntity totalBook) {
        return new BookMonthlyResDto(
                totalBook.getId(),
                totalBook.getYear(),
                totalBook.getMonth(),
                totalBook.getBudget(),
                totalBook.getRemainBudget()
        );
    }
}

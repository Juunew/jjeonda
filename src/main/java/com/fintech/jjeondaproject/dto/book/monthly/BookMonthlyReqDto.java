package com.fintech.jjeondaproject.dto.book.monthly;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Getter

public class BookMonthlyReqDto {

    private Long bookId;
    private String year;
    private String month;
    private int budget;

}

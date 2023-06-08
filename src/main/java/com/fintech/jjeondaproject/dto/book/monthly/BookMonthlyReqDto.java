package com.fintech.jjeondaproject.dto.book.monthly;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookMonthlyReqDto {

    private String year;
    private String month;
    private int budget;

}

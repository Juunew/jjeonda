package com.fintech.jjeondaproject.dto.book.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDetailModDto {

    private String costType;
    private int cost;
    private String content;
    private String memo;
}

package com.fintech.jjeondaproject.dto.book.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BookDetailReqDto {

    private Long bookId;
    private String costType;
    private int cost;
    private String content;
    private String memo;
}

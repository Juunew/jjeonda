package com.fintech.jjeondaproject.dto.book.detail;

import lombok.AllArgsConstructor;
import lombok.Getter;



@AllArgsConstructor
@Getter

public class BookDetailReqDto {

    private Long bookId;
    private Long userId;
    private String costType; //유형
    private int cost;       //내역
    private String content; //사용금액
    private String memo;    //메모


}

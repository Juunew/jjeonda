package com.fintech.jjeondaproject.dto.book;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class BookReqDto {

    private String costType;
    private String content;
    private int cost;
    private String memo;
}

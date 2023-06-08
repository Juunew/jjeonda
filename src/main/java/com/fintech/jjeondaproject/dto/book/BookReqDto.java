package com.fintech.jjeondaproject.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
public class BookReqDto {
// 가계부 작성 페이지에서 사용할 것 들만
    private String costType;
    private String content;
    private int cost;
    private String memo;

    // setCost 메서드 추가
    public void setCost(int cost) {
        this.cost = cost;
    }
}

package com.fintech.jjeondaproject.common.constant.errorType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ArticleError {

    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다");

    private final HttpStatus status;
    private final String message;
}

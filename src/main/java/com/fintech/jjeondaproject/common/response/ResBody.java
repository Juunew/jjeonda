package com.fintech.jjeondaproject.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResBody<T> {

    private String resultCode;
    private T result;

    public static ResBody<Void> error(String errorCode) {
        return new ResBody<>(errorCode, null);
    }

    public static ResBody<Void> success() {
        return new ResBody<>("SUCCESS", null);
    }

    public static <T> ResBody<T> success(T result) {
        return new ResBody<>("SUCCESS", result);
    }
}

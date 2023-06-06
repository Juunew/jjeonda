package com.fintech.jjeondaproject.dto.card;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardTestDto {

    private Long id;
    private String user;

    @Data
    public static class test {
        public String nickName;
    }
}

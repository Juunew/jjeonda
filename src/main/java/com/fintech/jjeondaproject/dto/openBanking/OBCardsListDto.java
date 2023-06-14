package com.fintech.jjeondaproject.dto.openBanking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OBCardsListDto {
    private String user_seq_no;
    private Object card_list;
}

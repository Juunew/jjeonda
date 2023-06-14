package com.fintech.jjeondaproject.dto.openBanking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OBCardsBillsDto {
    private String user_seq_no;
    private Object bill_list;
}

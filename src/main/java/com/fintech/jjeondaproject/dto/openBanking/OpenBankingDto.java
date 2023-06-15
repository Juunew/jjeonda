package com.fintech.jjeondaproject.dto.openBanking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OpenBankingDto {
    // 오픈뱅킹 설명서 있는 result
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String user_seq_no;
    private Long user_id;
}

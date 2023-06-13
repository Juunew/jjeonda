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
//	private Long id;
    private String access_token;
    //	private byte[] access_token = new byte[500];
    private String token_type;
    private String expires_in;
    private String refresh_token;
    //	private byte[] refresh_token = new byte[500];
    private String scope;
    private String user_seq_no;
}

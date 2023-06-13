package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.openBanking.OpenBankingDto;
import com.fintech.jjeondaproject.entity.openBanking.OBTokenEntity;
import com.fintech.jjeondaproject.feign.OpenBankingFeign;
import com.fintech.jjeondaproject.repository.openBankingRepository.OpenBankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenBankingService {
    private final OpenBankingRepository openBankingRepository;
    private final OpenBankingFeign openBankingFeign;

    public void requestToken(String code, String scope, String state){
        OpenBankingDto openBankingDto =
                openBankingFeign.requestToken(code,"86dd1ec4-2394-4815-963f-0e5d2c28428a"
                        , "c3cb34d6-8b7d-4e3e-b2e7-aabf2f3d9f2d"
                        , "http://localhost:8080/requesttoken", "authorization_code");

        System.out.println("토큰responseVO : " + openBankingDto.toString());
        insertToken(openBankingDto);
    }

    //    public void insertToken(TokenReponseVO tokenReponseVO){
    public void insertToken(OpenBankingDto openBankingDto){
        OBTokenEntity oBTokenEntity = OBTokenEntity.builder()
                        .accessToken("Bearer " + openBankingDto.getAccess_token())
//                        .tokenType(openBankingDto.getToken_type())
                        .expiresIn(Integer.parseInt(openBankingDto.getExpires_in()))
                        .refreshToken(openBankingDto.getRefresh_token())
//                        .scope(openBankingDto.getScope())
                        .userSeqNo(Long.parseLong(openBankingDto.getUser_seq_no()))
                        .build();
        openBankingRepository.save(oBTokenEntity);
    }

    public void cardList(){

    }



//    public String getToken(long i) {
//        Optional<CardTokenEntity> tokenEntity = bankingRepository.findById(i);
//        return tokenEntity.get().getAccess_token();
//    }
}

package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.openBanking.OpenBankingDto;
import com.fintech.jjeondaproject.entity.openBanking.OBTokenEntity;
import com.fintech.jjeondaproject.feign.OpenBankingFeign;
import com.fintech.jjeondaproject.repository.openBankingRepository.OpenBankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OpenBankingService {
    private final OpenBankingRepository openBankingRepository;
    private final OpenBankingFeign openBankingFeign;

    @Value ("${open-banking.client-id}")
    String client_id;
    @Value ("${open-banking.client-secret}")
    String client_secret;
    @Value ("${open-banking.redirect_uri}")
    String redirect_uri;




    public void requestToken(String code, String scope, String state){
        OpenBankingDto openBankingDto =
                openBankingFeign.requestToken(code
                        , client_id
                        , client_secret
                        , redirect_uri
                        , "authorization_code");

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

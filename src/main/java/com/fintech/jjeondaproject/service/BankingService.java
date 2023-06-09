package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.openBanking.BankCodeDto;
import com.fintech.jjeondaproject.entity.openBanking.CardTokenEntity;
import com.fintech.jjeondaproject.repository.BankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BankingService {
    private final BankingRepository bankingRepository;

    //    public void insertToken(TokenReponseVO tokenReponseVO){
    public void insertToken(CardTokenEntity tokenEntity){
//        TokenEntity tokenEntity = TokenEntity.builder()
//                        .accessToken(tokenReponseVO.getAccess_token())
//                        .tokenType(tokenReponseVO.getToken_type())
//                        .expiresIn(Integer.parseInt(tokenReponseVO.getExpires_in()))
//                        .refreshToken(tokenReponseVO.getRefresh_token())
//                        .scope(tokenReponseVO.getScope())
//                        .userSeqNo(Long.parseLong(tokenReponseVO.getUser_seq_no()))
//                        .build();
        bankingRepository.save(tokenEntity);
    }



//    public String getToken(long i) {
//        Optional<CardTokenEntity> tokenEntity = bankingRepository.findById(i);
//        return tokenEntity.get().getAccess_token();
//    }
}

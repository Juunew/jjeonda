package com.fintech.jjeondaproject.service;

import com.fintech.jjeondaproject.dto.openBanking.OBCardsListDto;
import com.fintech.jjeondaproject.dto.openBanking.OpenBankingDto;
import com.fintech.jjeondaproject.entity.openBanking.OBTokenEntity;
import com.fintech.jjeondaproject.feign.OpenBankingFeign;
import com.fintech.jjeondaproject.repository.openBankingRepository.OpenBankingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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




    public void requestToken(Long id, String code, String scope, String state){
        OpenBankingDto openBankingDto =
                openBankingFeign.requestToken(code
                        , client_id
                        , client_secret
                        , redirect_uri
                        , "authorization_code");

        openBankingDto.setUser_id(id); // 로그인한 유저 id
        insertToken(openBankingDto);
    }

    // DTO에 전체 값 가져와서 entity에 저장하기
    public void cardList(){


        //Authorization(토큰)
        //bank_tran_id = M202201118U000000001
        //user_seq_no = openBankingRepository.findByUserId(로그인토큰아이디).getUserSeqNo();
        //bank_code_std =
        //member_bank_code
        ////OBCardsListDto obCardsListDto = openBankingFeign.requestCardsList(// 요청값);
        /////OBCardsIssueInfoDto obCardsIssueInfoDto = openBankingFeign.requestCardsIssueInfo(//요청값);
        ////OBCardsBillsDto obCardsBillsDto = openBankingFeign.requestCardsBills(//요청값);
        /////OBCardsBillsDetail obCardsBillsDetail = openBankingFeign.requestCardsBillsDetail(//요청값);

        //cardEntity에 저장해서 테이블에 저장하면 끝

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
                        .userId(openBankingDto.getUser_id())
                        .build();
        openBankingRepository.save(oBTokenEntity);
    }

    public List<OpenBankingDto> findByUserId(Long userId) {

        List<OBTokenEntity> obTokenEntityList = openBankingRepository.findByUserId(userId);
        return obTokenEntityList.stream().
                map(m->new OpenBankingDto(
                        m.getAccessToken(),
                        String.valueOf(m.getExpiresIn()),
                        m.getRefreshToken(),
                        String.valueOf(m.getUserSeqNo()),
                        m.getUserId()
                ))
                .collect(Collectors.toList());
    }


//    public String getToken(long i) {
//        Optional<CardTokenEntity> tokenEntity = bankingRepository.findById(i);
//        return tokenEntity.get().getAccess_token();
//    }
}

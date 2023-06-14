package com.fintech.jjeondaproject.controller;

import com.fintech.jjeondaproject.common.UserInfo;
import com.fintech.jjeondaproject.config.annotation.InfoUser;
import com.fintech.jjeondaproject.service.OpenBankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OpenBankingController {
    private final OpenBankingService openBankingService;

    //open_banking Accesstoken 받기
    @GetMapping("/requesttoken")
    public String requesttoken(@InfoUser UserInfo userInfo,
                               @RequestParam("code") String code,
                               @RequestParam("scope") String scope,
                               @RequestParam("state") String state
    ) {
        System.out.println("code : " + code);
        System.out.println("scope : " + scope);
        System.out.println("state : " + state);

//        BankCodeDto = bankingService.findClientSecret();
        openBankingService.requestToken(userInfo.getUserId(), code, scope, state);

        return "index";
    }

    // Tokken을 이용한 카드 정보 가져오기(우리가 필요한 것만)
//    @GetMapping
//    public


//    @GetMapping("/cardList")
//    public String cardInfo(@RequestParam("bank_tran_id") String bank_tran_id,
//                           @RequestParam("user_seq_no") String user_seq_no,
//                           @RequestParam("bank_code_std") String bank_code_std,
//                           @RequestParam("member_bank_code") String member_bank_code
//    ) {
//
//        System.out.println("bank_tran_id : " + bank_tran_id);
//        System.out.println("user_seq_no : " + user_seq_no);
//        System.out.println("bank_code_std : " + bank_code_std);
//        System.out.println("member_bank_code : " + member_bank_code);
//
//        String accessToken = "Bearer " + bankingService.getToken(1);
//        System.out.println("accessToken : " + accessToken);
//
//        CardListResponseVO cardListResponseVO = bankingFeign.cardListResponse(accessToken, bank_tran_id, user_seq_no, bank_code_std, member_bank_code);
//
//        System.out.println("카드listResponseVO : " + cardListResponseVO.toString());
////		bankingService.insertToken(tokenResponseVO);
//
//        return "home";
//    }
}

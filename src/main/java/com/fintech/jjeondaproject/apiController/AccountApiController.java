package com.fintech.jjeondaproject.apiController;

import com.fintech.jjeondaproject.common.response.ResBody;
import com.fintech.jjeondaproject.dto.account.AccountListDto;
import com.fintech.jjeondaproject.dto.account.AccountResDto;
import com.fintech.jjeondaproject.service.AccountTestService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Accounts", description = "계좌 관리 API")
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
@RestController
public class AccountApiController {

    private final AccountTestService accountService;

    // 보유 계좌목록 조회
    @GetMapping("/list/{userId}")
    public ResBody<?> getMyAccountsList(@PathVariable Long userId) {
        List<AccountListDto> result = accountService.findMyAccountList(userId);
        return ResBody.success(result);
    }

    // 상세 조회
    @GetMapping("/detail/{userId}/{bankId}")
    public ResBody<?> getMyAccountDetail(@PathVariable Long userId,
                                         @PathVariable Long bankId) {
        List<AccountResDto> result = accountService.findMyAccountDetail(userId, bankId);
        return ResBody.success(result);
    }
    
    // 계좌 추가
    
    
}


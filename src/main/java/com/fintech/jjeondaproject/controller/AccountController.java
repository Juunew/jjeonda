package com.fintech.jjeondaproject.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fintech.jjeondaproject.dto.account.AccountDto;
import com.fintech.jjeondaproject.entity.AccountEntity;
import com.fintech.jjeondaproject.repository.AccountRepository;
import com.fintech.jjeondaproject.service.AccountService;
import com.fintech.jjeondaproject.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/accounts")
@Controller
public class AccountController {
	private final AccountService accountService;
	
	//전체계좌목록
	@GetMapping("/list")
	public String accountList(Model model) {
		List<AccountDto> accountDto = accountService.accountList();
		model.addAttribute("accountList", accountDto);
		return "account/accountList";
	}
	
	//상세내역 보기
	@GetMapping("detail/{accountId}")
	public String accountDetail(@PathVariable("accountId") String accountId, Model model) {
		AccountDto accountDto = accountService.selectOneByAccountId(Long.parseLong(accountId));
		model.addAttribute("accountList", Collections.singletonList(accountDto)); 
											//단일객체를 리스트로 감싸는 것은 일관성있는 데이터 처리를 위해 권장방법
		return "account/accountDetail";
	}
	

	/*
	 * //거래내역조회 API
	 * 
	 * @GetMapping("/transaction") public String transacton() { return ("/"); }
	 * 
	 * //잔액조회 API
	 * 
	 * @GetMapping("/balance") public String balance() { return balance(); }
	 */
	
	 
	
	
	
		/*
		 * model.addAttribute("accountDetail", accountDetail(model)); return
		 * ("/accountDetail");
		 */
	
		/*
		 * // accountId 가져오기
		 * 
		 * @GetMapping("/account") public String getAccount(Model model) { String
		 * accountId = accountService.toString(); model.addAttribute("accountId",
		 * accountId); return ("account/accountDetail"); }
		 */

}

	
	  
	



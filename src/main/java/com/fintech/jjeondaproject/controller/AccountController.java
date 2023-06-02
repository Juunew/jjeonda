package com.fintech.jjeondaproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fintech.jjeondaproject.dto.account.AccountDto;
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
		System.out.println(accountDto.toString());
		return ("/AccountTest");
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
	  
	
}

package com.fintech.jjeondaproject.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fintech.jjeondaproject.common.UserInfo;
import com.fintech.jjeondaproject.config.annotation.InfoUser;
import com.fintech.jjeondaproject.dto.account.AccountDto;
import com.fintech.jjeondaproject.dto.account.AccountReqDto;
import com.fintech.jjeondaproject.dto.account.NoAccountIdDto;
import com.fintech.jjeondaproject.entity.bank.BankEntity;
import com.fintech.jjeondaproject.service.AccountService;
import com.fintech.jjeondaproject.service.BankService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/accounts")
@Controller
public class AccountController {
	private final AccountService accountService;
	private final BankService bankService;
	
	//전체계좌목록
	@GetMapping("/list")
	public String accountList(Model model,
							 @InfoUser UserInfo userInfo) {
		
		List<AccountDto> accountDto = accountService.accountList(userInfo);
		model.addAttribute("accountList", accountDto);
		
		return "account/accountList";
	}
	
	//상세내역 보기
	@GetMapping("/detail/{accountId}")
	public String accountDetail(@PathVariable("accountId") String accountId, Model model) {
		AccountDto accountDto = accountService.selectOneByAccountId(Long.parseLong(accountId));
		model.addAttribute("accountList", Collections.singletonList(accountDto)); 
											//단일객체를 리스트로 감싸는 것은 일관성있는 데이터 처리를 위해 권장방법
		return "account/accountDetail";
	}

	// 계좌 직접 추가
	@GetMapping("/addAccount")
    public String AddAccountForm(Model model) {
    	List<BankEntity> bankList = bankService.getAllBanks();
    	model.addAttribute("bankList", bankList);
		
        return "account/addAccount";
    }
	@PostMapping("/addAccount")
	public String AddAccountForm(@ModelAttribute AccountReqDto reqDto,
									/* @ModelAttribute NoAccountIdDto noAccountIdDto,*/
	                         		 @InfoUser UserInfo userInfo) {
		System.out.println("reqDto: " + reqDto.toString());
	   accountService.addAccount(reqDto, userInfo);
	   
	   return "redirect:/accounts/list";
	}
	
	// 계좌 삭제
	@PostMapping("/deleteAccount")
	public String deleteAccount(@RequestParam("deleteId")Long accountId) {
		System.out.println("accountID = " + accountId);
		accountService.deleteAccount(accountId);
		
		return "redirect:/accounts/list";
	}
	
	
		
		
		
		
		
/*	
	@PostMapping("/deleteAccount/{accountId}")
	public String deleteAccount(Model model,@RequestParam() Long[] deleteId) throws Exception{
		try {
			accountService.deleteById(Long accountId);
		} catch (Exception e){
			throw new Exception(e.getMessage());
		}
		return "redirect:/account/list";
	}
	*/
}	
	





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

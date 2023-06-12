package com.fintech.jjeondaproject.controller;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fintech.jjeondaproject.dto.account.AccountDto;
import com.fintech.jjeondaproject.dto.account.AccountFormDto;
import com.fintech.jjeondaproject.dto.account.AccountRequestDto;
import com.fintech.jjeondaproject.dto.bank.BankDto;
import com.fintech.jjeondaproject.entity.AccountEntity;
import com.fintech.jjeondaproject.repository.AccountRepository;
import com.fintech.jjeondaproject.repository.BankRepository;
import com.fintech.jjeondaproject.service.AccountService;
import com.fintech.jjeondaproject.service.BankService;
import com.fintech.jjeondaproject.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	// 계좌 직접 추가
	@GetMapping("/addAccount/")
    public String getAddAccountForm(Model model) {
        List<BankDto> banks = accountService.findAllBanks();
        model.addAttribute("banks", banks);
        model.addAttribute("accountForm", new AccountFormDto());
        return "account/addAccount";
    }
	
    @PostMapping("/addAccount")
    public String processAddAccountForm(@ModelAttribute("accountForm") AccountFormDto accountFormDto, RedirectAttributes redirectAttributes) {
        BankDto bankDto = accountService.getBankByCode(accountFormDto.getBankCode());
        if (bankDto == null) {
            redirectAttributes.addFlashAttribute("error", "Invalid bank code");
            return "redirect:/accounts/add";
        }

        AccountDto accountDto = accountService.registerAccount(accountFormDto.getAccountNum(), bankDto, accountFormDto.getAvailableAmt());
        redirectAttributes.addFlashAttribute("success", "Account registered successfully");
        return "redirect:/accounts/success";
    }

    @GetMapping("/success")
    public String showSuccessPage() {
        return "account/success";
    }
	
	// 계좌 삭제
	@GetMapping("delete/{accountId}")
	public String delete(@PathVariable ("accountId") String accountId, Model model) {
		accountService.delete(accountId);
		return "rdirect:/account/list";
	}
}	




/*
@GetMapping("/update")
public String updateGet(Model model) {
	model.addAttribute("account", accountService.selectOneByAccountId(accountId));
	return "account/update";
}

@PostMapping("/update")
public String updatePost(AccountDto accountDto) {
	accountService.update();
	return "redirect:/account/list";
}
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


	
	  
	



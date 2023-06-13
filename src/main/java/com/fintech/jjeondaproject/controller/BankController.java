package com.fintech.jjeondaproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fintech.jjeondaproject.dto.bank.BankDto;
import com.fintech.jjeondaproject.entity.bank.BankEntity;
import com.fintech.jjeondaproject.service.BankService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/banks")
@Controller
public class BankController {
	private final BankService bankService;
	
//	@GetMapping("/list")
//	public String bankList(Model model) {
//		List<BankEntity> bankList = bankService.getAllBanks();
//		model.addAttribute("bankList", bankList);
//		return "/banks/bankList";
//	}
	
}



package com.fintech.jjeondaproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fintech.jjeondaproject.dto.user.UserDto;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.service.RegisterMail;
import com.fintech.jjeondaproject.service.UserService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class UserController {
	private final UserService userService;
	private final RegisterMail registerMail;
	
	@GetMapping("/sign-up")
	public String joinForm(String agreementYn) {
		System.out.println("agreementYn:"+agreementYn);
		return "join";
	}
	
	@PostMapping("/sign-up")
	public String join(UserDto userDto, @RequestParam("agreementYn") String agreementYn) {
		userDto.setAgreementYn(agreementYn);
		userService.join(userDto);
		return "redirect:/";
	}
	// 약관동의
	@GetMapping("/agreement")
	public String agreement() {
		return "agreement";
	}
	@PostMapping("/agreement")
	public String agreements(@RequestParam("agreementYn") String agreementYn, RedirectAttributes re) {
		System.out.println("agreementYn-postmapping:"+ agreementYn);
		re.addAttribute("agreementYn", agreementYn);
		
		return "redirect:/sign-up";
	}
	
	// ID 중복체크
	@ResponseBody
	@PostMapping("/checkId")
	public boolean checkId(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println("id:"+id);
		return userService.checkId(id);
	}
	
	// email 인증
	@PostMapping("/mailConfirm")
	@ResponseBody
	public String mailConfirm(@RequestParam("email") String email) throws Exception {
	   String code = registerMail.sendSimpleMessage(email);
	   System.out.println("인증코드 : " + code);
	   return code;
	}
	
	// 메인페이지
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	// 로그인 페이지
	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}
	
}

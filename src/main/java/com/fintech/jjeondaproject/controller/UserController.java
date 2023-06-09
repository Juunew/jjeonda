package com.fintech.jjeondaproject.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fintech.jjeondaproject.dto.user.ProfileResponseDto;
import com.fintech.jjeondaproject.dto.user.UserDto;
import com.fintech.jjeondaproject.dto.user.UserLoginDto;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.service.RegisterMail;
import com.fintech.jjeondaproject.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequiredArgsConstructor
@Controller
public class UserController {
	private final UserService userService;
	private final RegisterMail registerMail;
	
	@GetMapping("/sign-up")
	public String joinForm(String agreementYn) {
		return "user/join";
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
		return "user/agreement";
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
		String accountId = request.getParameter("id");
		System.out.println("accountId:"+accountId);
		return userService.checkAccountId(accountId);
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
	public String home(HttpServletResponse res, HttpServletRequest req) {
//		System.out.println("indexToken:"+token);
		System.out.println("indexCookie:"+req.getCookies());
		
		return "index";
	}
	
	// 로그인 페이지
	@GetMapping("/sign-in")
	public String loginForm() {
		return "user/login";
	}
	
	@PostMapping("/sign-in")
	public String login(UserLoginDto userDto, HttpServletResponse response) {
		String token = userService.signIn(userDto);
		Cookie tokenCookie = new Cookie("JwToken",token);
		response.addCookie(tokenCookie);
		
		return "redirect:/";
	}
	
	// 카카오로그인
	@PostMapping("/login/kakao")
	public ProfileResponseDto getCode(@RequestParam String code) {
		return userService.getRequireUrl();
	}
	
	
}

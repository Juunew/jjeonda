package com.fintech.jjeondaproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fintech.jjeondaproject.dto.user.UserDto;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.service.UserService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class UserController {
	private final UserService userService;
	
	@GetMapping("/sign-up")
	public String joinForm() {
		return "join";
	}
	
	@PostMapping("/sign-up")
	public String join(UserDto userDto) {
		userService.join(userDto);
		return "redirect:/";
	}
	
	@ResponseBody
	@PostMapping("/checkId")
	public boolean checkId(HttpServletRequest request) {
		String id = request.getParameter("id");
		System.out.println("id:"+id);
		return userService.checkId(id);
	}
		
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
}

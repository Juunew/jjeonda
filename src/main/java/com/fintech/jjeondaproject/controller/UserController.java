package com.fintech.jjeondaproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fintech.jjeondaproject.dto.user.UserDto;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.service.UserService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class UserController {
	private final UserService userService;
	
	@GetMapping("/join")
	public String joinForm() {
		return "join";
	}
	
	@PostMapping("/join1")
	public String join(UserDto userDto) {
		userService.join(userDto);
		return "redirect:/home";
	}
		
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
}

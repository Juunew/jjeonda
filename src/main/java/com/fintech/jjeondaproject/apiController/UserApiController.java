package com.fintech.jjeondaproject.apiController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fintech.jjeondaproject.common.response.ResBody;
import com.fintech.jjeondaproject.dto.user.ProfileResponseDto;
import com.fintech.jjeondaproject.dto.user.UserDto;
import com.fintech.jjeondaproject.dto.user.UserLoginDto;
import com.fintech.jjeondaproject.repository.UserRepository;
import com.fintech.jjeondaproject.service.RegisterMail;
import com.fintech.jjeondaproject.service.UserService;

import antlr.collections.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Users", description = "회원 관리 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserApiController {
    private final UserService userService;
    private final RegisterMail registerMail;
    
    @PostMapping("/sign-up")
    public ResBody<?> join(@RequestBody @Valid UserDto userDto, @RequestParam("agreementYn") String agreementYn) {
    	userDto.setAgreementYn(agreementYn);
        userService.join(userDto);
        return ResBody.success();
    }

    @PostMapping("/agreement")
    public ResBody<?> agreements(@RequestParam("agreementYn") String agreementYn) {
    	return ResBody.success(agreementYn);
    }

    @PostMapping("/checkId")
    public boolean checkId(HttpServletRequest request) {
        String accountId = request.getParameter("id");
        return userService.checkAccountId(accountId);
    }
    


    @PostMapping("/mailConfirm")
    public ResBody<?> mailConfirm(@RequestParam("email") String email) throws Exception {
        String code = registerMail.sendSimpleMessage(email);
        return ResBody.success(code);
    }



    @PostMapping("/sign-in")
    public ResBody<?> login(@RequestBody UserLoginDto userDto, HttpServletResponse response) {
        String token = userService.signIn(userDto);
        Cookie tokenCookie = new Cookie("JwToken",token);
		response.addCookie(tokenCookie);
        return ResBody.success();
    }

}

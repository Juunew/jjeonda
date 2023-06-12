package com.fintech.jjeondaproject.apiController;

import com.fintech.jjeondaproject.common.response.ResBody;
import com.fintech.jjeondaproject.dto.user.UserDto;
import com.fintech.jjeondaproject.dto.user.UserLoginDto;
import com.fintech.jjeondaproject.service.RegisterMail;
import com.fintech.jjeondaproject.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Tag(name = "Users", description = "회원 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
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
    public ResponseEntity<?> agreements(@RequestParam("agreementYn") String agreementYn) {
        return ResponseEntity.ok().build();
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
    public ResBody<?> login(@RequestBody UserLoginDto userDto) {
        String token = userService.signIn(userDto);
        return ResBody.success(token);
    }

}

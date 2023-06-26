package com.fintech.jjeondaproject.apiController;

import com.fintech.jjeondaproject.common.response.ResBody;
import com.fintech.jjeondaproject.dto.user.UserReqDto;
import com.fintech.jjeondaproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResBody<?> createUser(@RequestBody UserReqDto reqDto) {
        Long userId = userService.createUser(reqDto);
        return ResBody.success(userId);
    }
}

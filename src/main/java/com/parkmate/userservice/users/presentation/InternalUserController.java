package com.parkmate.userservice.users.presentation;

import com.parkmate.userservice.common.response.ApiResponse;
import com.parkmate.userservice.users.application.UserService;
import com.parkmate.userservice.users.dto.request.UserRegisterRequestDto;
import com.parkmate.userservice.users.dto.request.UserRegisterSocialRequestDto;
import com.parkmate.userservice.users.vo.request.UserRegisterRequestVo;
import com.parkmate.userservice.users.vo.request.UserRegisterSocialRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/users")
@RequiredArgsConstructor
public class InternalUserController {

    private final UserService userService;

    @Operation(
            summary = "유저 등록",
            description = "Auth-service에서 openFeign으로 전송받은 UserRegisterRequestVo로 유저를 등록하는 API 입니다.",
            tags = "{USER-SERVICE}"
    )
    @PostMapping
    public ApiResponse<String> register(@RequestBody UserRegisterRequestVo userRegisterRequestVo) {
        userService.createUser(UserRegisterRequestDto.from(userRegisterRequestVo));

        return ApiResponse.of(
                HttpStatus.OK,
                "유저를 등록하였습니다"
        );
    }

    @PostMapping("/social")
    public ApiResponse<String> registerSocial(@RequestBody UserRegisterSocialRequestVo userRegisterSocialRequestVo) {
        userService.createSocialUser(UserRegisterSocialRequestDto.from(userRegisterSocialRequestVo));

        return ApiResponse.of(
                HttpStatus.OK,
                "소셜 유저를 등록하였습니다."
        );
    }

}
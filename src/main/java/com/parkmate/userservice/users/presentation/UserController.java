package com.parkmate.userservice.users.presentation;

import com.parkmate.userservice.common.response.ApiResponse;
import com.parkmate.userservice.users.application.UserService;
import com.parkmate.userservice.users.dto.request.UserUpdateRequestDto;
import com.parkmate.userservice.users.vo.request.UserUpdateRequestVo;
import com.parkmate.userservice.users.vo.response.UserGetNameResponseVo;
import com.parkmate.userservice.users.vo.response.UserGetPointResponseVo;
import com.parkmate.userservice.users.vo.response.UserGetResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "유저 수정",
            description = "userUuid를 PathVariable로 받고, 수정할 유저 정보를 RequestBody로 받아 유저 정보를 수정하는 API 입니다.",
            tags = {"USER-SERVICE"}
    )
    @PutMapping
    public ApiResponse<String> updateUser(@RequestHeader("X-User-UUID") String userUuid,
                                          @RequestBody UserUpdateRequestVo userUpdateRequestVo) {

        userService.updateUser(UserUpdateRequestDto.from(userUuid, userUpdateRequestVo));

        return ApiResponse.of(
                HttpStatus.OK,
                "유저를 수정하였습니다."
        );
    }

    @Operation(
            summary = "유저 조회",
            description = "userUuid를 PathVariable로 받아 해당 유저 정보를 조회하는 API 입니다.",
            tags = {"USER-SERVICE"}
    )
    @GetMapping
    public ApiResponse<UserGetResponseVo> getUserByUuid(@RequestHeader("X-User-UUID") String userUuid) {

        return ApiResponse.ok(
                userService.findUserByUuid(userUuid).toVo()
        );
    }

    @Operation(
            summary = "유저 이름 조회",
            description = "X-User-UUID 헤더에서 유저 UUID를 받아 유저 이름을 조회합니다.",
            tags = {"USER-SERVICE"}
    )
    @GetMapping("/name")
    public ApiResponse<UserGetNameResponseVo> getUserName(@RequestHeader("X-User-UUID") String userUuid) {

        return ApiResponse.ok(
                userService.findUserNameByUuid(userUuid).toVo()
        );
    }

    @Operation(
            summary = "유저 포인트 조회",
            description = "X-User-UUID 헤더에서 유저 UUID를 받아 유저 포인트를 조회합니다.",
            tags = {"USER-SERVICE"}
    )
    @GetMapping("/point")
    public ApiResponse<UserGetPointResponseVo> getUserPoint(@RequestHeader("X-User-UUID") String userUuid) {

        return ApiResponse.ok(
                userService.findUserPointByUuid(userUuid).toVo());
    }

    @Operation(
            summary = "유저 삭제",
            description = "userUuid를 PathVariable로 받아 해당 유저 정보를 삭제하는 API 입니다.",
            tags = {"USER-SERVICE"}
    )
    @DeleteMapping
    public ApiResponse<String> deleteUser(@RequestHeader("X-User-UUID") String userUuid) {
        userService.deleteUser(userUuid);

        return ApiResponse.of(
                HttpStatus.OK,
                "유저를 삭제하였습니다."
        );
    }

}
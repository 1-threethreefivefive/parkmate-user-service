package com.parkmate.userservice.users.presentation;

import com.parkmate.userservice.common.response.ApiResponse;
import com.parkmate.userservice.users.application.UserService;
import com.parkmate.userservice.users.dto.request.UserUpdateRequestDto;
import com.parkmate.userservice.users.vo.request.UserUpdateRequestVo;
import com.parkmate.userservice.users.vo.response.UserGetResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PutMapping("/{userUuid}")
    public ApiResponse<String> updateUser(@PathVariable String userUuid,
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
    @GetMapping("/{userUuid}")
    public ApiResponse<UserGetResponseVo> getUserByUuid(@PathVariable String userUuid) {

        return ApiResponse.ok(
                userService.findUserByUuid(userUuid).toVo()
        );
    }

    @Operation(
            summary = "유저 삭제",
            description = "userUuid를 PathVariable로 받아 해당 유저 정보를 삭제하는 API 입니다.",
            tags = {"USER-SERVICE"}
    )
    @DeleteMapping("/{userUuid}")
    public ApiResponse<String> deleteUser(@PathVariable String userUuid) {
        userService.deleteUser(userUuid);

        return ApiResponse.of(
                HttpStatus.OK,
                "유저를 삭제하였습니다."
        );
    }

    @Operation(
            summary = "사용자 이름 변경",
            description = "사용자의 이름을 변경하고, 변경된 내용을 Kafka 이벤트로 발행합니다.",
            tags = {"USER-SERVICE"}
    )
    @PutMapping("/name")
    public ResponseEntity<Void> updateUserName(
            @RequestHeader("X-User-UUID") String userUuid,
            @RequestBody UserUpdateRequestDto request
    ) {
        userService.updateUserProfile(userUuid, request.getName());
        return ResponseEntity.ok().build();
    }
}
package com.parkmate.userservice.userfavorite.presentation;

import com.parkmate.userservice.common.response.ApiResponse;
import com.parkmate.userservice.userfavorite.application.UserFavoriteService;
import com.parkmate.userservice.userfavorite.dto.request.UserFavoriteDeleteRequestDto;
import com.parkmate.userservice.userfavorite.dto.request.UserFavoriteRegisterRequestDto;
import com.parkmate.userservice.userfavorite.dto.response.UserFavoriteResponseDto;
import com.parkmate.userservice.userfavorite.vo.request.UserFavoriteDeleteRequestVo;
import com.parkmate.userservice.userfavorite.vo.request.UserFavoriteRegisterRequestVo;
import com.parkmate.userservice.userfavorite.vo.response.UserFavoriteResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserFavoriteController {

    private final UserFavoriteService userFavoriteService;

    @Operation(
            summary = "즐겨찾기 등록",
            description = "X-User-UUID 헤더에서 유저 UUID를 받아 즐겨찾기를 등록합니다.",
            tags = {"USER-FAVORITE-SERVICE"}
    )
    @PostMapping("/favorites")
    public ApiResponse<String> registerFavorite(@RequestHeader("X-User-UUID") String userUuid,
                                                @RequestBody UserFavoriteRegisterRequestVo userFavoriteRegisterRequestVo) {

        userFavoriteService.register(UserFavoriteRegisterRequestDto.from(userUuid, userFavoriteRegisterRequestVo));

        return ApiResponse.of(
                HttpStatus.CREATED,
                "즐겨찾기를 등록하였습니다."
        );
    }

    @Operation(
            summary = "즐겨찾기 목록 조회",
            description = "X-User-UUID 헤더에서 유저 UUID를 받아 해당 사용자의 즐겨찾기 주차장 목록을 조회합니다.",
            tags = {"USER-FAVORITE-SERVICE"}
    )
    @GetMapping("/favorites")
    public ApiResponse<List<UserFavoriteResponseVo>> getFavorites(@RequestHeader("X-User-UUID") String userUuid) {
        List<UserFavoriteResponseDto> userFavoriteResponseDtoList = userFavoriteService.findParkingLotListByUserUuid(userUuid);
        List<UserFavoriteResponseVo> userFavoriteResponseVoList = new ArrayList<>();

        for (UserFavoriteResponseDto userFavoriteResponseDto : userFavoriteResponseDtoList) {
            userFavoriteResponseVoList.add(userFavoriteResponseDto.toVo());
        }

        return ApiResponse.ok(
                userFavoriteResponseVoList
        );
    }

    @Operation(
            summary = "즐겨찾기 삭제",
            description = "X-User-UUID 헤더에서 유저 UUID를 받아 즐겨찾기를 삭제합니다.",
            tags = {"USER-FAVORITE-SERVICE"}
    )
    @DeleteMapping("/favorites")
    public ApiResponse<String> deleteFavorite(@RequestHeader("X-User-UUID") String userUuid,
                                              @RequestBody UserFavoriteDeleteRequestVo userFavoriteDeleteRequestVo) {

        userFavoriteService.deleteFavorite(UserFavoriteDeleteRequestDto.from(userUuid, userFavoriteDeleteRequestVo.getParkingLotUuid()));

        return ApiResponse.of(
                HttpStatus.OK,
                "즐겨찾기를 삭제했습니다."
        );
    }

}

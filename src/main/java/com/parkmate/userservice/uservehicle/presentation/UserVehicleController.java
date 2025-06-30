package com.parkmate.userservice.uservehicle.presentation;

import com.parkmate.userservice.common.response.ApiResponse;
import com.parkmate.userservice.uservehicle.application.UserVehicleService;
import com.parkmate.userservice.uservehicle.dto.request.UserVehicleDeleteRequestDto;
import com.parkmate.userservice.uservehicle.dto.request.UserVehicleGetRequestDto;
import com.parkmate.userservice.uservehicle.dto.request.UserVehicleRegisterRequestDto;
import com.parkmate.userservice.uservehicle.dto.request.UserVehicleSetDefaultDto;
import com.parkmate.userservice.uservehicle.vo.request.UserVehicleRegisterRequestVo;
import com.parkmate.userservice.uservehicle.vo.response.UserVehicleGetAllResponseVo;
import com.parkmate.userservice.uservehicle.vo.response.UserVehicleGetResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserVehicleController {

    private final UserVehicleService userVehicleService;

    @Operation(
            summary = "내 차량 등록",
            description = "사용자의 차량을 등록하는 API입니다. 기본 차량 여부를 포함하여 등록합니다.",
            tags = {"USER-VEHICLE"}
    )
    @PostMapping("/userVehicle")
    public ApiResponse<String> registerVehicle(
            @RequestHeader("X-User-UUID") String userUuid,
            @RequestBody UserVehicleRegisterRequestVo userVehicleRegisterRequestVo
    ) {
        userVehicleService.register(UserVehicleRegisterRequestDto.of(userUuid,
                userVehicleRegisterRequestVo));

        return ApiResponse.of(
                HttpStatus.CREATED,
                "차량 등록이 완료되었습니다"
        );
    }

    @Operation(
            summary = "내 차량 삭제",
            description = "차량 UUID를 기준으로 사용자의 차량을 soft delete하는 API입니다.",
            tags = {"USER-VEHICLE"}
    )
    @DeleteMapping("/userVehicle/{vehicleUuid}")
    public ApiResponse<String> deleteVehicle(
            @PathVariable String vehicleUuid,
            @RequestHeader("X-User-UUID") String userUuid
    ) {
        userVehicleService.delete(UserVehicleDeleteRequestDto
                .of(vehicleUuid, userUuid));

        return ApiResponse.of(
                HttpStatus.NO_CONTENT,
                "차량 삭제가 완료되었습니다"
        );
    }

    @Operation(
            summary = "내 차량 리스트 조회",
            description = "사용자의 모든 차량을 조회하는 API입니다. soft delete된 차량은 제외됩니다.",
            tags = {"USER-VEHICLE"}
    )
    @GetMapping("/userVehicle")
    public ApiResponse<List<UserVehicleGetAllResponseVo>> getAllVehicle(
            @RequestHeader("X-User-UUID") String userUuid
    ) {
        return ApiResponse.ok(
                userVehicleService.getAllVehicles(userUuid).stream()
                        .map(UserVehicleGetAllResponseVo::toVo)
                        .toList()
        );
    }

    @Operation(
            summary = "내 차량 단건 조회",
            description = "차량 UUID를 기준으로 사용자의 차량 상세 정보를 조회하는 API입니다.",
            tags = {"USER-VEHICLE"}
    )
    @GetMapping("/userVehicle/{vehicleUuid}")
    public ApiResponse<UserVehicleGetResponseVo> getVehicle(
            @PathVariable String vehicleUuid,
            @RequestHeader("X-User-UUID") String userUuid
    ) {

        return ApiResponse.ok(
                userVehicleService.getVehicle(UserVehicleGetRequestDto
                        .of(userUuid, vehicleUuid)).toVo()
        );
    }

    @Operation(
            summary = "기본 차량으로 등록",
            description = "지정된 차량을 기본 차량으로 설정하는 API입니다. 기존 기본 차량이 있을 경우 변경됩니다.",
            tags = {"USER-VEHICLE"}
    )
    @PutMapping("/userVehicle/{vehicleUuid}")
    public ApiResponse<String> setDefault(
            @PathVariable String vehicleUuid,
            @RequestHeader("X-User-UUID") String userUuid
    ) {
        userVehicleService.setDefault(UserVehicleSetDefaultDto.of(userUuid, vehicleUuid));
        return ApiResponse.ok(
                "기본 차량 등록되었습니다."
        );

    }
}

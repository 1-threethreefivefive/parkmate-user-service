package com.parkmate.userservice.uservehicle.dto.request;

import com.parkmate.userservice.uservehicle.domain.UserVehicle;
import com.parkmate.userservice.uservehicle.vo.request.UserVehicleRegisterRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class UserVehicleRegisterRequestDto {

    private String userUuid;
    private String vehicleNumber;
    private String nickname;
    private boolean defaultSelected;

    @Builder
    private UserVehicleRegisterRequestDto(String userUuid,
                                          String vehicleNumber,
                                          String nickname,
                                          boolean defaultSelected) {
        this.userUuid = userUuid;
        this.vehicleNumber = vehicleNumber;
        this.nickname = nickname;
        this.defaultSelected = defaultSelected;
    }

    public static UserVehicleRegisterRequestDto of(String userUuid,
                                                   UserVehicleRegisterRequestVo userVehicleRegisterRequestVo) {
        return UserVehicleRegisterRequestDto.builder()
                .userUuid(userUuid)
                .vehicleNumber(userVehicleRegisterRequestVo.getVehicleNumber())
                .nickname(userVehicleRegisterRequestVo.getNickname())
                .defaultSelected(userVehicleRegisterRequestVo.isDefaultSelected())
                .build();
    }

    public UserVehicle toEntity() {
        return UserVehicle.builder()
                .vehicleUuid(UUID.randomUUID().toString())
                .userUuid(userUuid)
                .vehicleNumber(vehicleNumber)
                .nickname(nickname)
                .defaultSelected(defaultSelected)
                .build();
    }

}
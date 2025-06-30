package com.parkmate.userservice.uservehicle.vo.response;

import com.parkmate.userservice.uservehicle.dto.response.UserVehicleGetAllResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserVehicleGetAllResponseVo {

    private String vehicleUuid;

    @Builder
    private UserVehicleGetAllResponseVo(String vehicleUuid) {
        this.vehicleUuid = vehicleUuid;
    }

    public static UserVehicleGetAllResponseVo toVo(UserVehicleGetAllResponseDto userVehicleGetAllResponseDto) {
        return UserVehicleGetAllResponseVo.builder()
                .vehicleUuid(userVehicleGetAllResponseDto.getVehicleUuid())
                .build();
    }

}

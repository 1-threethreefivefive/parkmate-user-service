package com.parkmate.userservice.uservehicle.dto.response;

import com.parkmate.userservice.uservehicle.domain.UserVehicle;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserVehicleGetAllResponseDto {

    private String vehicleUuid;

    @Builder
    private UserVehicleGetAllResponseDto(String vehicleUuid) {
        this.vehicleUuid = vehicleUuid;
    }

    public static UserVehicleGetAllResponseDto from(UserVehicle userVehicle) {
        return UserVehicleGetAllResponseDto.builder()
                .vehicleUuid(userVehicle.getVehicleUuid())
                .build();
    }

}

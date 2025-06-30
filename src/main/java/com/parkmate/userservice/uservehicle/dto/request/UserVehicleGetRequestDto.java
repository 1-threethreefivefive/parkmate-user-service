package com.parkmate.userservice.uservehicle.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserVehicleGetRequestDto {

    private String userUuid;
    private String vehicleUuid;

    @Builder
    private UserVehicleGetRequestDto(String userUuid,
                                     String vehicleUuid) {
        this.userUuid = userUuid;
        this.vehicleUuid = vehicleUuid;
    }

    public static UserVehicleGetRequestDto of(String userUuid,
                                              String vehicleUuid) {
        return UserVehicleGetRequestDto.builder()
                .userUuid(userUuid)
                .vehicleUuid(vehicleUuid)
                .build();
    }

}

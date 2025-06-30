package com.parkmate.userservice.uservehicle.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserVehicleDeleteRequestDto {

    private String vehicleUuid;
    private String userUuid;

    @Builder
    private UserVehicleDeleteRequestDto(String vehicleUuid,
                                        String userUuid) {
        this.vehicleUuid = vehicleUuid;
        this.userUuid = userUuid;
    }

    public static UserVehicleDeleteRequestDto of(String vehicleUuid, String userUuid) {
        return UserVehicleDeleteRequestDto.builder()
                .vehicleUuid(vehicleUuid)
                .userUuid(userUuid)
                .build();
    }

}

package com.parkmate.userservice.uservehicle.dto.request;

import com.parkmate.userservice.uservehicle.domain.UserVehicle;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserVehicleSetDefaultDto {

    private String userUuid;
    private String vehicleUuid;

    @Builder
    private UserVehicleSetDefaultDto(String userUuid,
                                     String vehicleUuid) {
        this.userUuid = userUuid;
        this.vehicleUuid = vehicleUuid;
    }

    public static UserVehicleSetDefaultDto of(String userUuid,
                                              String vehicleUuid) {
        return UserVehicleSetDefaultDto.builder()
                .userUuid(userUuid)
                .vehicleUuid(vehicleUuid)
                .build();
    }

    public UserVehicle toEntity() {
        return UserVehicle.builder()
                .userUuid(userUuid)
                .vehicleUuid((vehicleUuid))
                .build();
    }

}

package com.parkmate.userservice.uservehicle.infrastructure;

import com.parkmate.userservice.uservehicle.domain.UserVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserVehicleRepository extends JpaRepository<UserVehicle, Long> {

    Optional<UserVehicle> findByUserUuidAndDefaultSelectedTrue(String userUuid);

    Optional<UserVehicle> findByUserUuidAndVehicleUuidAndDeletedSelectedFalse(String userUuid, String vehicleUuid);

    Optional<UserVehicle> findByUserUuidAndVehicleNumberAndDeletedSelectedTrue(String userUuid, String vehicleUuid);

    List<UserVehicle> findAllByUserUuidAndDeletedSelectedFalse(String userUuid);

}

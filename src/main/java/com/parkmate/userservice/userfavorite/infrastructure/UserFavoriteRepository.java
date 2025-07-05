package com.parkmate.userservice.userfavorite.infrastructure;

import com.parkmate.userservice.userfavorite.domain.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long>, UserFavoriteCustomRepository {

    Optional<UserFavorite> findByUserUuid(String userUuid);

    List<UserFavorite> findAllByUserUuidAndIsDeletedFalse(String userUuid);

    Optional<UserFavorite> findByUserUuidAndParkingLotUuid(String userUuid, String parkingLotUuid);

    Optional<UserFavorite> findByUserUuidAndParkingLotUuidAndIsDeletedFalse(String userUuid, String parkingLotUuid);

}

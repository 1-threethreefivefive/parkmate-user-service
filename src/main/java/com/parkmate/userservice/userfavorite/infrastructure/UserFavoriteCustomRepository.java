package com.parkmate.userservice.userfavorite.infrastructure;

import com.parkmate.userservice.common.response.CursorPage;
import com.parkmate.userservice.userfavorite.domain.UserFavorite;
import com.parkmate.userservice.userfavorite.dto.request.UserFavoriteGetRequestDto;

public interface UserFavoriteCustomRepository {

    CursorPage<UserFavorite> getParkingLotUuidsByUserUuidAndIsDeletedFalse(UserFavoriteGetRequestDto userFavoriteGetRequestDto);

}

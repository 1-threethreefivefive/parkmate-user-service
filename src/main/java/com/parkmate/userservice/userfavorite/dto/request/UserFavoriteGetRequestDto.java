package com.parkmate.userservice.userfavorite.dto.request;

import com.parkmate.userservice.userfavorite.vo.request.UserFavoriteGetRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserFavoriteGetRequestDto {

    private String userUuid;
    private String cursor;
    private Integer size;

    @Builder
    public UserFavoriteGetRequestDto(String userUuid,
                                     String cursor,
                                     Integer size) {
        this.userUuid = userUuid;
        this.cursor = cursor;
        this.size = size;
    }

    public static UserFavoriteGetRequestDto of(String userUuid, UserFavoriteGetRequestVo userFavoriteGetRequestVo) {
        return UserFavoriteGetRequestDto.builder()
                .userUuid(userUuid)
                .cursor(userFavoriteGetRequestVo.getCursor())
                .size(userFavoriteGetRequestVo.getSize())
                .build();
    }

}

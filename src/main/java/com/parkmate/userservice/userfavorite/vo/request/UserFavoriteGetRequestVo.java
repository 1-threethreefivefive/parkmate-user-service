package com.parkmate.userservice.userfavorite.vo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserFavoriteGetRequestVo {

    private String cursor;
    private Integer size;

}

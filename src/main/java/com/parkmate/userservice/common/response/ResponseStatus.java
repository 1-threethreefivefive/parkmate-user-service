package com.parkmate.userservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    /**
     * 2xx: 요청 성공
     **/
    SUCCESS(HttpStatus.OK, true, 200, "요청에 성공하였습니다."),

    /**
     * 4xx: 클라이언트 오류
     **/
    FAILED_TO_FIND_USER(HttpStatus.NOT_FOUND, false, 404, "해당 유저를 찾을 수 없습니다."),
    FAILED_TO_FIND_VEHICLE(HttpStatus.NOT_FOUND, false, 404, "자동차를 찾을 수 없습니다."),
    ALREADY_EXIST_FAVORITE(HttpStatus.CONFLICT, false, 409, "이미 즐겨찾기가 존재합니다."),
    ALREADY_EXIST_USER(HttpStatus.CONFLICT, false, 409, "이미 유저가 존재합니다."),

    /**
     * 5xx: 서버 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 500, "서버 내부 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final int code;
    private final String message;

}
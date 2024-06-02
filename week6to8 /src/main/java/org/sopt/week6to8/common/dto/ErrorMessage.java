package org.sopt.week6to8.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사용자가 존재하지 않습니다."),
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 블로그가 없습니다."),
    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패했습니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.FORBIDDEN.value(), "올바르지 않은 리프레시 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 리프레시 토큰이 없습니다."),
    ;
    private final int status;
    private final String message;
}

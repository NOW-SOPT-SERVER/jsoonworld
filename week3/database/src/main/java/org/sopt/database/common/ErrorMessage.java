package org.sopt.database.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    MEMBER_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사람이 없습니다"),
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "[ERROR] 타이틀 오류");

    private final int status;
    private final String message;
}


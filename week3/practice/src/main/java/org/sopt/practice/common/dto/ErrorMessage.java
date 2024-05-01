package org.sopt.practice.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    MEMBER_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사람이 없습니다"),
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "[ERROR] 타이틀 오류"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 ID의 포스트를 찾을 수 없습니다.");


    private final int status;
    private final String message;
}


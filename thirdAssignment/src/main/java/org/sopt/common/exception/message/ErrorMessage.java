package org.sopt.common.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 사용자가 존재하지 않습니다."),
    BLOG_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당 블로그가 존재하지 않습니다."),

    INVALID_TOKEN(HttpStatus.BAD_REQUEST.value(), "유효하지 않은 토큰 값입니다."),
    ;

    int status;
    String message;
}

package org.sopt.common.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessMessage {
    BLOG_CREATE_SUCCESS(HttpStatus.CREATED.value(), "블로그 생성이 완료되었습니다."),
    BLOG_GET_SUCCESS(HttpStatus.OK.value(), "블로그 조회가 완료되었습니다."),

    MEMBER_CREATE_SUCCESS(HttpStatus.CREATED.value(), "회원 생성이 완료되었습니다."),
    MEMBER_DELETE_SUCCESS(HttpStatus.NO_CONTENT.value(), "회원 삭제가 완료되었습니다"),
    MEMBER_GET_SUCCESS(HttpStatus.OK.value(), "회원 조회가 완료되었습니다."),

    POST_CREATE_SUCCESS(HttpStatus.CREATED.value(), "게시물 생성이 완료되었습니다."),
    POST_GET_ALL_SUCCESS(HttpStatus.OK.value(), "게시물 리스트 조회가 완료되었습니다."),
    ;

    int status;
    String message;
}

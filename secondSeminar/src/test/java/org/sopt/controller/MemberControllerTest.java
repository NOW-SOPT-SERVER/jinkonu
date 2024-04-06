package org.sopt.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.domain.Member;
import org.sopt.repository.MemberRepository;
import org.sopt.service.MemberService;
import org.sopt.service.dto.MemberCreateDto;
import org.sopt.settings.ApiTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberControllerTest extends ApiTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Nested
    @DisplayName("멤버 생성 테스트")
    public class MemberCreate {

        @DisplayName("요청 성공 케이스")
        @Test
        void createMemberSuccess() throws Exception {
            // given
            final var request = new MemberCreateDto(
                    "konu",
                    Member.Part.SERVER,
                    26
            );

            // when
            final var response = RestAssured
                    .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON.getType())
                    .body(request)
                    .when()
                    .post("/api/v1/member")
                    .then()
                    .log().all()
                    .extract();

            // then
            assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        }
    }
}

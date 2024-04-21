package org.sopt.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.domain.Part;
import org.sopt.dto.request.MemberCreateRequest;
import org.sopt.repository.MemberRepository;
import org.sopt.service.MemberService;
import org.sopt.settings.ApiTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.sopt.domain.Part.SERVER;

class MemberControllerTest extends ApiTest {

    static String name = "konu";
    static Part part = SERVER;
    static int age = 26;

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    @Nested
    @DisplayName("멤버 생성 테스트")
    public class CreateMember {

        @Test
        @DisplayName("요청 성공 테스트")
        public void createMemberSuccess() throws Exception {
            // given
            final var request = new MemberCreateRequest(name, part, age);

            // when
            final var response = RestAssured
                    .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(request)
                    .when()
                    .post("/api/v1/member")
                    .then().log().all().extract();

            // then
            assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        }
    }

    @Nested
    @DisplayName("멤버 조회 테스트")
    public class GetMember {

        static String id;
        static int createCount = 0;

        @BeforeEach
        public void init() {
            id = memberService.createMember(new MemberCreateRequest(name, part, age));
            ++createCount;
        }

        @Test
        @DisplayName("멤버 단건 조회 요청 성공 테스트")
        public void getMemberSuccess() throws Exception {
            // when
            final var response = RestAssured
                    .given()
                    .log().all()
                    .when()
                    .get("/api/v1/member/{memberId}", id)
                    .then().log().all().extract();

            // then
            assertThat(response.body().jsonPath().getString("name")).isEqualTo(name);
            assertThat(response.body().jsonPath().getString("part")).isEqualTo(part.toString());
            assertThat(response.body().jsonPath().getString("age")).isEqualTo(String.valueOf(age));
        }

        @Test
        @DisplayName("멤버 리스트 조회 요청 성공 테스트")
        public void getMemberListSuccess() throws Exception {
            // given
            IntStream.range(0, 10)
                    .forEach(i -> init());

            // when
            final var response = RestAssured
                    .given()
                    .log().all()
                    .when()
                    .get("/api/v1/member")
                    .then().log().all().extract();

            // then
            assertThat(response.body().jsonPath().getString("count")).isEqualTo(String.valueOf(createCount));
        }
    }

    @Nested
    @DisplayName("멤버 삭제 테스트")
    public class deleteMember {

        static String id;

        @BeforeEach
        public void init() {
            id = memberService.createMember(new MemberCreateRequest(name, part, age));
        }

        @Test
        @DisplayName("요청 성공 테스트")
        public void deleteMemberSuccess() throws Exception {
            // when
            final var response = RestAssured
                    .given()
                    .log().all()
                    .when()
                    .delete("/api/v1/member/{memberId}", id)
                    .then().log().all().extract();

            // then
            assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
        }
    }
}
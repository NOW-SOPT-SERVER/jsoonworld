package org.sopt.api.controller;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.sopt.api.domain.Member;
import org.sopt.api.domain.Part;
import org.sopt.api.repository.MemberRepository;
import org.sopt.api.service.MemberService;
import org.sopt.api.service.dto.MemberCreateDto;
import org.sopt.api.service.dto.MemberFindDto;
import org.sopt.api.settings.ApiTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberControllerTest extends ApiTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Nested
    @DisplayName("멤버 생성 테스트")
    public class CreateMember {

        @Test
        @DisplayName("요청 성공 테스트")
        public void createMemberSuccess() throws Exception {
            //given
            final var request = new MemberCreateDto(
                    "권장순",
                    Part.SERVER,
                    26
            );

            //when
            final var response = RestAssured
                    .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(request)
                    .when()
                    .post("/api/v1/member")
                    .then().log().all().extract();
            //then
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        }
    }

    @Nested
    @DisplayName("모든 멤버 조회 테스트")
    public class FindAllMembers {

        @Test
        @DisplayName("모든 멤버 조회 성공")
        public void findAllMemberSuccess() {

            //given
            List<MemberFindDto> expectedMembers = List.of(
                    new MemberFindDto("남궁찬", Part.DESIGN, 30),
                    new MemberFindDto("강현욱", Part.IOS, 21),
                    new MemberFindDto("양대한", Part.ANDROID, 27)
            );
            memberRepository.saveAll(List.of(
                    new Member("남궁찬", Part.DESIGN, 30),
                    new Member("강현욱", Part.IOS, 21),
                    new Member("양대한", Part.ANDROID, 27)
            ));

            //when
            final var response = RestAssured
                    .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .when()
                    .get("/api/v1/member/all")
                    .then().log().all().extract();

            //then
            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
            Assertions.assertThat(
                    response.jsonPath().getList("", MemberFindDto.class))
                    .usingRecursiveFieldByFieldElementComparator().isEqualTo(expectedMembers);
        }

    }
}
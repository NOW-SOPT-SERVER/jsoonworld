package org.sopt.week6to8.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.week6to8.service.AuthService;
import org.sopt.week6to8.service.dto.MemberCreateDto;
import org.sopt.week6to8.service.dto.UserJoinResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<UserJoinResponse> signUp(
            @RequestBody MemberCreateDto memberCreate
    ) {
        UserJoinResponse userJoinResponse = authService.signUp(memberCreate);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", userJoinResponse.userId())
                .body(
                        userJoinResponse
                );
    }

    @PostMapping("/reissue")
    public ResponseEntity<UserJoinResponse> reissueToken(
            @RequestHeader("X-Refresh-Token") final String refreshToken
    ) {
        try {
            UserJoinResponse userJoinResponse = authService.signIn(refreshToken);
            return ResponseEntity.ok(userJoinResponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

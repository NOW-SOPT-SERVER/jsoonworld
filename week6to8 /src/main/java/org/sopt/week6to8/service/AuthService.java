package org.sopt.week6to8.service;

import lombok.RequiredArgsConstructor;
import org.sopt.week6to8.auth.UserAuthentication;
import org.sopt.week6to8.auth.redis.domain.Token;
import org.sopt.week6to8.auth.redis.repository.ReFreshTokenRepository;
import org.sopt.week6to8.common.jwt.JwtTokenProvider;
import org.sopt.week6to8.domain.Member;
import org.sopt.week6to8.repository.MemberRepository;
import org.sopt.week6to8.service.dto.MemberCreateDto;
import org.sopt.week6to8.service.dto.UserJoinResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ReFreshTokenRepository reFreshTokenRepository;

    @Transactional
    public UserJoinResponse signUp(
            MemberCreateDto memberCreate
    ) {
        Member member = memberRepository.save(
                Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age())
        );
        Long memberId = member.getId();
        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberId)
        );
        String refreshToken = jwtTokenProvider.issueRefreshToken();
        reFreshTokenRepository.save(Token.of(memberId, refreshToken));

        return UserJoinResponse.of(accessToken, refreshToken, memberId.toString());
    }

    @Transactional
    public UserJoinResponse signIn(
            String refreshToken
    ) {
        Token redisToken = reFreshTokenRepository.findByRefreshTokenOrElseThrow(refreshToken);
        String newAccessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(redisToken.getId())
        );

        return UserJoinResponse.of(newAccessToken, refreshToken, redisToken.getId().toString());
    }
}


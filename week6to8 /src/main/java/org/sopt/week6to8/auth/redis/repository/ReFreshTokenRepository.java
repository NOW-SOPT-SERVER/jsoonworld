package org.sopt.week6to8.auth.redis.repository;

import org.sopt.week6to8.auth.redis.domain.Token;
import org.sopt.week6to8.common.dto.ErrorMessage;
import org.sopt.week6to8.exception.NotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReFreshTokenRepository extends CrudRepository<Token, Long> {

    default Token findByRefreshTokenOrElseThrow(final String refreshToken) {
        return findByRefreshToken(refreshToken).orElseThrow(() -> new NotFoundException(ErrorMessage.REFRESH_TOKEN_NOT_FOUND));
    }

    Optional<Token> findByRefreshToken(final String refreshToken);
    Optional<Token> findById(final Long id);

}

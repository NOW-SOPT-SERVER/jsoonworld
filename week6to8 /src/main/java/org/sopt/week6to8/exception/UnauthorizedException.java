package org.sopt.week6to8.exception;

import org.sopt.week6to8.common.dto.ErrorMessage;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

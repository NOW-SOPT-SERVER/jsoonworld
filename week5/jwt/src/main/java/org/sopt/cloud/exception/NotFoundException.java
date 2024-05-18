package org.sopt.cloud.exception;


import org.sopt.cloud.common.ErrorMessage;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);

    }
}

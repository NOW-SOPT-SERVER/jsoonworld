package org.sopt.database.exception;

import org.sopt.database.common.ErrorMessage;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);

    }
}

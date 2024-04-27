package org.sopt.cloud.common.dto;


import org.sopt.cloud.common.ErrorMessage;

public record ErrorResponse(
        int status,
        String message
) {

    public static ErrorResponse of(int status, String message) {
        return new ErrorResponse(status, message);
    }

    public static ErrorResponse of(ErrorMessage errorMessage) {
        return new ErrorResponse(errorMessage.getStatus(), errorMessage.getMessage());
    }
}

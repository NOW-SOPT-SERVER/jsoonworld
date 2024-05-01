package org.sopt.practice.common.dto;

public record SuccessStatusResponse<T>(
        int status,
        String message,
        T data
) {
    public static <T> SuccessStatusResponse<T> of(SuccessMessage successMessage, T data) {
        return new SuccessStatusResponse<>(successMessage.getStatus(), successMessage.getMessage(), data);
    }
}

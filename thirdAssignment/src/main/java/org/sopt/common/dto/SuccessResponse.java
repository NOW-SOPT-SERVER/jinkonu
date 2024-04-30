package org.sopt.common.dto;

import org.sopt.common.exception.message.SuccessMessage;

public record SuccessResponse<T>(
        int status,
        String message,
        T data
) {
    public static <T> SuccessResponse of(SuccessMessage successMessage, T data) {
        return new SuccessResponse(successMessage.getStatus(), successMessage.getMessage(), data);
    }

    public static SuccessResponse of(SuccessMessage successMessage) {
        return new SuccessResponse(successMessage.getStatus(), successMessage.getMessage(), null);
    }
}

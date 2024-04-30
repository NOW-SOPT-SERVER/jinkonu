package org.sopt.common.dto;

import org.sopt.common.exception.message.ErrorMessage;

public record ErrorResponse(
        int status,
        String message

) {
    public static ErrorResponse of(ErrorMessage errorMessage) {
        return new ErrorResponse(errorMessage.getStatus(), errorMessage.getMessage());
    }
}

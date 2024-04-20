package org.sopt.common.dto.response;

public record SuccessStatusResponse(
        int status,
        String message
) {
    public static SuccessStatusResponse of(SuccessMessage message) {
        return new SuccessStatusResponse(message.getStatus(), message.getMessage());
    }
}

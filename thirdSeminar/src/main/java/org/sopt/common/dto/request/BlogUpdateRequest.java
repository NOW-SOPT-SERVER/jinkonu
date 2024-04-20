package org.sopt.common.dto.request;

import jakarta.validation.constraints.Size;

public record BlogUpdateRequest(
        @Size(min = 1, max = 5)
        String title,
        @Size(min = 1, max = 255)
        String description
) {
}

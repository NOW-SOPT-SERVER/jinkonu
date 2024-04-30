package org.sopt.common.exception;

import lombok.Getter;
import org.sopt.common.exception.message.ErrorMessage;

@Getter
public class SoptException extends RuntimeException {

    private ErrorMessage errorMessage;

    public SoptException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}

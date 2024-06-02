package org.sopt.common.exception;

import lombok.Getter;
import org.sopt.common.exception.message.ErrorMessage;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}

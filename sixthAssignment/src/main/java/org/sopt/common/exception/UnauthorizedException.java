package org.sopt.common.exception;

import org.sopt.common.exception.message.ErrorMessage;

public class UnauthorizedException extends BusinessException {

    public UnauthorizedException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

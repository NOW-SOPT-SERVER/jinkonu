package org.sopt.common.exception;

import org.sopt.common.exception.message.ErrorMessage;

public class InvalidTokenException extends SoptException {

    public InvalidTokenException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

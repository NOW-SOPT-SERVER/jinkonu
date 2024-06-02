package org.sopt.exception;

import org.sopt.exception.message.ErrorMessage;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

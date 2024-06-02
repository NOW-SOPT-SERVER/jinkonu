package org.sopt.common.exception;

import org.sopt.common.exception.message.ErrorMessage;

public class NotFoundException extends BusinessException {

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

package org.sopt.common.exception;

import org.sopt.common.exception.message.ErrorMessage;

public class NotFoundException extends SoptException {

    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

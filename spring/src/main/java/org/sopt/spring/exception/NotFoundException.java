package org.sopt.spring.exception;

import org.sopt.spring.common.dto.ErrorMessage;

public class NotFoundException extends BusinessException {
    public NotFoundException(ErrorMessage errorMessage) {
        super(errorMessage);
    }
}

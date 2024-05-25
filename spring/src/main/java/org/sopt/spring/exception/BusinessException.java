package org.sopt.spring.exception;

import lombok.Getter;
import org.sopt.spring.common.dto.ErrorMessage;

@Getter
public class BusinessException extends RuntimeException{
    private ErrorMessage errorMessage;

    public BusinessException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        this.errorMessage = errorMessage;
    }
}

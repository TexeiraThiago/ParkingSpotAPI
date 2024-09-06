package com.api.parking_control.services.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }
}

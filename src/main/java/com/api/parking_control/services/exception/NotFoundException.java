package com.api.parking_control.services.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super("Parking Spot not found");
    }
}
